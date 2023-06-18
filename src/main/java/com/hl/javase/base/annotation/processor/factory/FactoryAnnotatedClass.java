package com.hl.javase.base.annotation.processor.factory;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;

import org.springframework.util.StringUtils;

import com.hl.javase.base.annotation.processor.Factory;

/**
 * 
 * @author huanglin 2023/05/02 下午5:08:33
 *
 */
public class FactoryAnnotatedClass {

	private TypeElement annotatedElement;
	private String      qulifiedSuperClassName;
	private String      simpleTypeName;
	private String      id;
	
	public FactoryAnnotatedClass(TypeElement classElement) {
		this.annotatedElement = classElement;
		Factory annotation    = classElement.getAnnotation(Factory.class);
		id = annotation.id();
		
		if(StringUtils.isEmpty(id)) {
			throw new IllegalArgumentException(
					String.format("id() in @%s for class %s is null or empty! that's not allowed",
							Factory.class.getSimpleName(), classElement.getQualifiedName().toString()));
		}
		try {
			Class<?> clazz         = annotation.type();
			qulifiedSuperClassName = clazz.getCanonicalName();
			simpleTypeName         = clazz.getSimpleName();
		} catch (MirroredTypeException e) {
			DeclaredType classTypeMirror  = (DeclaredType) e.getTypeMirror();
			TypeElement  classTypeElement = (TypeElement) classTypeMirror.asElement();
			qulifiedSuperClassName        = classTypeElement.getQualifiedName().toString();
			simpleTypeName                = classTypeElement.getSimpleName().toString();
		}
	}
	
	/**
	 * 获取在{@link Factory#id()}中指定的id
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * 获取在{@link Factory#type()}指定的类型合法全名
	 * 
	 * @return
	 */
	public String getQualifiedFactoryGroupName() {
		return qulifiedSuperClassName;
	}
	
	/**
	 * 获取在 {@link Factory#type()} 中指定的类型的简单名字
	 * @return
	 */
	public String getSimpleFactoryGroupName() {
		return simpleTypeName;
	}
	
	/**
	 * 获取被@Factory注解的原始元素
	 * @return
	 */
	public TypeElement getTypeElement() {
		return annotatedElement;
	}
}
