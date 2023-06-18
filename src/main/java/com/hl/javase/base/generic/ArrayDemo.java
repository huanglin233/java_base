package com.hl.javase.base.generic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author huanglin 2023/04/06 下午10:20:12
 *
 */
public class ArrayDemo {

	public static <T> T[] fun1(T ...args) {
		return args;
	}
	
	public static <T> void fun2(T param[]) {
		System.out.println("fun2");
		for(T t : param) {
			System.out.print(t + "/");
		}
	}
	
	public static <T> T[] arrayWithTypeToken(Class<T> type, int size) {
		return (T[]) Array.newInstance(type, size);
	}
	
	public static void main(String[] args) {
//		List<String>[] list1 = new ArrayList<String>[10]; // 编译错误,非法创建
//		List<String>[] list2 = new ArrayList<?>[10]; // 编译错误,需要强转类型
		List<String>[] list3 = (List<String>[]) new ArrayList<?>[10]; // 不报错,但是会有告警
//		List<?>[] list4 = new ArrayList<String>()[10]; // 编译错误,非法创建
		List<?>[] list5 = new ArrayList<?>[10]; // 不报错
		List<String>[] list6 = new ArrayList[10];// 不报错,但是会有告警
		
		Integer i[] = fun1(1, 2, 3, 4, 5, 4, 7);
		fun2(i);
		
		String[] arrayWithTypeToken = arrayWithTypeToken(String.class, 10);
		System.out.println();
		System.out.println(arrayWithTypeToken);
	}
}
