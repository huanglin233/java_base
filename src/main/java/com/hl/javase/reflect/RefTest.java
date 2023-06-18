package com.hl.javase.reflect;

import java.lang.reflect.Field;

import com.hl.javase.reflect.test.Dog;

/**
 * 
 * @author huanglin 2023/05/10 下午9:30:04
 *
 */
public class RefTest {

	public static void main(String[] args) throws Exception {
//		userTest();
		animalTest();
	}
	
	private static void userTest() throws Exception {
		// 获取class对象的三种方式
		System.out.println("根据类名: \t" + RefUser.class);
		System.out.println("根据对象: \t" + new RefUser().getClass());
		System.out.println("根据全限定类名: \t" + Class.forName("com.hl.javase.reflect.RefUser"));
		// 常用的方法
		Class<?> rUser = Class.forName("com.hl.javase.reflect.RefUser");
		System.out.println("获取权限定类名: \t" + rUser.getName());
		System.out.println("获取类名: \t" + rUser.getSimpleName());
		System.out.println("实例化: \t" + rUser.newInstance());
	}
	
	private static void animalTest() throws Exception {
		Class<Dog> dog = Dog.class;
		// 类名打印
		System.out.println(dog.getName());
		System.out.println(dog.getSimpleName());
		System.out.println(dog.getCanonicalName());
		// 接口
		System.out.println(dog.isInterface());
		for(Class iI : dog.getInterfaces()) {
			System.out.println(iI);
		}
		/*
			interface com.hl.javase.reflect.test.I1
			interface com.hl.javase.reflect.test.I2
		 */
		
		// 父类
		System.out.println(dog.getSuperclass());
		// 创建对象
		Dog dog_ = dog.newInstance();
		// 字段
		for(Field f : dog.getFields()) {
			// 父类得公共字段也打印出来了
			System.out.println(f.getName());
		}
		/*
			dmDogPublic
			sDogPublic
			mAnimalPublic
			sAnimalPublic
			mCellPublic
		 */
		
		System.out.println();
		for(Field f : dog.getDeclaredFields()) {
			// 只打印自己声明的字段
			System.out.println(f.getName());
		}
		/*
			mDogPrivate
			mDogPublic
			mDogProtected
			mDogDefault
			sDogPrivate
			sDogProtected
			sDogPublic
			sDogDefault
		 */
	}
}
