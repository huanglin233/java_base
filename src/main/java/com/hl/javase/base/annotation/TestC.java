package com.hl.javase.base.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.checkerframework.checker.units.qual.radians;

/**
 * 
 * @author huanglin 2023/04/29 下午5:44:04
 *
 */
public class TestC {

	@MyAnnotation(title = "toStringMethod", description = "override toString method")
	@Override
	public String toString() {
		return "override toString method";
	}
	
	@MyAnnotation(title = "old static method", description = "deprecate old static method")
	@Deprecated
	public static void oldMethod() {
		System.out.println("old method, don't use it.");
	}
	
	@MyAnnotation(title = "test method", description = "suppress warning static method")
	@SuppressWarnings({"unchecked", "deprecation"})
	public static void genericsTest() {
		List l = new ArrayList<>();
		l.add("abc");
		oldMethod();
	}
	
	public static void main(String[] args) {
		// 时使用反射获取注解信息
		try {
			// 获取所有得methods
			Method[] methods = TestC.class.getClassLoader().loadClass("com.hl.javase.base.annotation.TestC")
				.getMethods();
			
			for(Method method : methods) {
				// 判断方法上是否有MyAnnotation注解
				if(method.isAnnotationPresent(MyAnnotation.class)) {
					try {
						// 获取并遍历方法行得注解
						for(Annotation annotation : method.getAnnotations()) {
							System.out.println("'Annotation in Method " + method + " ' : " + annotation);
						}
						
						//获取MyAnnotation对象信息
						MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
						System.out.println(annotation.title());
					} catch (Throwable e) {
						e.printStackTrace();
					}
					
					System.out.println();
				}
			}
		} catch (SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
