package com.hl.javase.base.annotation.processor.factory;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

import com.google.auto.service.AutoService;
import com.hl.javase.base.annotation.processor.Factory;

/**
 * 
 * @author huanglin 2023/04/29 下午9:51:12
 *
 */
@AutoService(Processor.class)
public class FactoryProcessor extends AbstractProcessor{

	/**
	 * 一个用来处理TypeMirror的工具类
	 */
	private Types typeUtils;
	/**
	 * 一个用来处理Element的工具类
	 */
	private Elements elementsUtils;
	/**
	 * 使用Filer创建文件
	 */
	private Filer filer;
	private Messager messager;
	private Map<String, FactoryGroupedClasses> factoryClasses = new LinkedHashMap<String, FactoryGroupedClasses>();
	
	/**
	 * 每一个注解处理器类都必须有一个空的构造函数。然而，这里有一个特殊的init()方法，
	 * 它会被注解处理工具调用，并输入ProcessingEnviroment参数。
	 * ProcessingEnviroment提供很多有用的工具类Elements, Types和Filer
	 */
	
	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		typeUtils     = processingEnv.getTypeUtils();
		elementsUtils = processingEnv.getElementUtils();
		filer         = processingEnv.getFiler();
		messager      = processingEnv.getMessager();
	}
	
	/**
	 * 这里你必须指定，这个注解处理器是注册给哪个注解的。
	 * 注意，它的返回值是一个字符串的集合，包含本处理器想要处理的注解类型的合法全称。
	 * 换句话说，你在这里定义你的注解处理器注册到哪些注解上。
	 * jdk1.7可使用此注解代替: @SupportedAnnotationTypes({ 合法注解全名的集合 })
	 */
	@Override
	public Set<String> getSupportedAnnotationTypes() {
		Set<String> annotations = new LinkedHashSet<String>();
		annotations.add(Factory.class.getCanonicalName());
		
		return annotations;
	}
	
	/**
	 * 用来指定你使用的Java版本
	 * jdk1.7可使用此注解代替: @SupportedSourceVersion(SourceVersion.latestSupported())
	 */
	@Override
	public SourceVersion getSupportedSourceVersion() {
		return SourceVersion.latestSupported();
	}
	
	/**
	 * 相当于每个处理器的主函数main()
	 */
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		// 遍历所有被注解了@Factory的元素
		for(Element annotatedElement : roundEnv.getElementsAnnotatedWith(Factory.class)) {
			// 检查被注解为@Factory的元素是否是一个类
			if(annotatedElement.getKind() != ElementKind.CLASS) {
				error(annotatedElement, "Only classes can be annotated with @%s", Factory.class.getSimpleName());
				
				return true; // 退出处理
			}
			
			// 因为我们已经知道它是ElementKid.CLASS类型,所以可以直接强制转换
			TypeElement typeElement = (TypeElement) annotatedElement;
			try {
				FactoryAnnotatedClass annotatedClass = new FactoryAnnotatedClass(typeElement);
				
				if(!isValidClass(annotatedClass)) {
					return true;
				}
				
				// 检查都没有问题,添加到注解类组合集合中
				FactoryGroupedClasses factoryClass = factoryClasses.get(annotatedClass.getQualifiedFactoryGroupName());
				if(factoryClass == null) {
					String qualifiedGroupName = annotatedClass.getQualifiedFactoryGroupName();
					factoryClass = new FactoryGroupedClasses(qualifiedGroupName);
					factoryClasses.put(qualifiedGroupName, factoryClass);
				}
				
				// 如果和其他@Factory标注的类的id相同冲突,抛出异常
				factoryClass.add(annotatedClass);
			} catch (IllegalArgumentException e) {
				// @Factroy.id() 为空
				error(typeElement, e.getMessage());
			} catch (ProcessingException e) {
				error(e.getElement(), e.getMessage());
			} catch (Exception e) {
				error(null, e.getMessage());
			}
		}
		
		try {
	        for (FactoryGroupedClasses factoryClass : factoryClasses.values()) {
	          factoryClass.generatedCode(elementsUtils, filer);
	        }
	    } catch (IOException e) {
	        error(null, e.getMessage());
	    }
		
		// 清楚factoryClasses
		factoryClasses.clear();
			
		return true;
	}
	
	/**
	 * 打印错误信息
	 * @param e
	 * @param msg
	 * @param args
	 */
	private void error(Element e, String msg, Object... args) {
		messager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args), e);
	}
	
	/**
	 * 用来检查被注解的类是否满足一下规则
	 * 	1.必须是公开类
	 *  2.必须是非抽象类
	 *  3.必须是@Factoy.type()指定的类型的子类或者接口的实现
	 *  4.类必须有一个公开的默认构造函数
	 * @param item
	 * @return
	 */
	private boolean isValidClass(FactoryAnnotatedClass item) {
		// 转化为TypeElement,含有更多特定的方法
		TypeElement classElement = item.getTypeElement();
		
		// 检查是否为公共类
		if(!classElement.getModifiers().contains(Modifier.PUBLIC)) {
			error(classElement, "The class %s is not public.", 
					classElement.getQualifiedName().toString());
			return false;
		}
		
		// 检查继承关系: 必须是@Factroy.type()指定的类型子类
		TypeElement superClassElement = elementsUtils.getTypeElement(item.getQualifiedFactoryGroupName());
		if(superClassElement.getKind() == ElementKind.INTERFACE) {
			// 检查接口是否实现了
			if(!classElement.getInterfaces().contains(superClassElement.asType())) {
				error(classElement, "The class %s annotated with @%s must implement then interface %s",
						classElement.getQualifiedName().toString(), Factory.class, item.getQualifiedFactoryGroupName());
			}
		} else {
			// 检查子类
			TypeElement currentClass = classElement;
			while(true) {
				TypeMirror superClassType = currentClass.getSuperclass();
				if(superClassType.getKind() == TypeKind.NONE) {
					// 到达了基本类型(java.lang.Object),退出
					error(classElement, "The class %s annotated with @%s must inherit from %s",
							classElement.getQualifiedName().toString(), Factory.class.getSimpleName(),
							item.getQualifiedFactoryGroupName());
					return false;
				}
				if(superClassType.toString().equals(item.getQualifiedFactoryGroupName())) {
					// 找到了要求的父类
					break;
				}
				
				// 在继承树上继续向上搜寻
				currentClass = (TypeElement) typeUtils.asElement(superClassType);
			}
		}
		
			// 检查是否提供了默认公开构造函数
		for(Element enclosed : classElement.getEnclosedElements()) {
			if(enclosed.getKind() == ElementKind.CONSTRUCTOR) {
				ExecutableElement constructorElement = (ExecutableElement) enclosed;
				if(constructorElement.getParameters().size() == 0 && constructorElement.getModifiers().contains(Modifier.PUBLIC)) {
					// 找到默认构造函数
					return true;
				}
			}
		}
		
		// 没有找到默认构造函数
		error(classElement, "The class %s must provide an public empty default constructor",
				classElement.getQualifiedName().toString());
		
		return false;
	}


}
