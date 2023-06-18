package com.hl.javase.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

/**
 * Constructor类及其用法
 * @author huanglin 2023/05/10 下午10:46:45
 *
 */
public class ReflectConstruction {

	public static void main(String[] args) throws Exception {
		Class<?> clazz = null;
		// 获取class对象引用
		clazz = Class.forName("com.hl.javase.reflect.RefUser");
		
		// 第一种方法,实例化默认构造方法,User必须无参构造函数,否则将抛异常
		RefUser refUser = (RefUser)clazz.newInstance();
		refUser.setAge(20);
		refUser.setName("Jack");
		System.out.println(refUser);
		System.out.println("-----------------------------------");
		
		// 获取带String参数的public构造函数
		Constructor<?> cRefUser = clazz.getDeclaredConstructor(String.class, int.class);
		// 由于是private必须设置可访问
		cRefUser.setAccessible(true);
		// 创建对象
		RefUser refUser2 = (RefUser) cRefUser.newInstance("hl", 28);
		System.out.println(refUser2);
		System.out.println("-----------------------------------");
		
		// 获取所有的构造包含private
		Constructor<?>[] cons = clazz.getDeclaredConstructors();
		// 或查看每个构造方法需要的参数
		for(int i = 0; i < cons.length; i++) {
			// 获取构造函数参数类型
			Class<?>[] clazzs = cons[i].getParameterTypes();
			System.out.println("构造函数[" + i + "]:" + cons[i].toString());
			System.out.print("参数类型[" + i + "]:(");
			for(int j = 0; j < clazzs.length; j++) {
				if(j == clazzs.length - 1) {
					System.out.print(clazzs[j].getName());
				} else {
					System.out.print(clazzs[j].getName() + ",");
				}
			}
			System.out.println(")");
		}
		
		Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(String.class, int.class);
		System.out.println("--------getDeclaringClass-------");
		Class uClazz = declaredConstructor.getDeclaringClass();
		// Constructor对象表示的构造方法
		System.out.println("构造方法的类:" + uClazz.getName());

		System.out.println("--------getGenericParameterTypes-------");
		// 对象表示此constructor对象所表示的方法的形参类型
		Type[] genericParameterTypes = declaredConstructor.getGenericParameterTypes();
		for(Type type : genericParameterTypes) {
			System.out.println("参数名称type: " + type);
		}

		// 获取构造函数参数类型
		System.out.println("-----getParameterTypes-----");
		Class<?>[] parameterTypes = declaredConstructor.getParameterTypes();
		for(Class cla : parameterTypes) {
			System.out.println("参数名称: " + cla.getName());
		}
		// 以字符串形式返回此构造方法的名称
		System.out.println("-----getName-----");
		System.out.println("getName: " + declaredConstructor.getName());

		// 返回描述此 Constructor 的字符串，其中包括类型参数
		System.out.println("-----getoGenericString-----");
		System.out.println("getoGenericString(): " + declaredConstructor.toGenericString());
	}
}
