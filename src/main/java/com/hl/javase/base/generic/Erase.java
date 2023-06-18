package com.hl.javase.base.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author huanglin 2023/04/06 下午11:07:57
 *
 */
public class Erase {

	public static void main(String[] args) throws Exception {
		ArrayList<String> list1 = new ArrayList<String>();
		list1.add("abc");
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		list2.add(13);
		System.out.println(list1.getClass() == list2.getClass()); // 类型擦除为原始类型
		
		ArrayList<Integer> list3 = new ArrayList<Integer>();
		list3.add(1);
		// 通过反射可以插入字符串,编译之后integer泛型进行擦除
		list3.getClass().getMethod("add", Object.class).invoke(list3, "abc");
		for(int i = 0; i < list3.size(); i++) {
			System.out.println(list3.get(i));
		}
		
		/**
		 * 不指定泛型的情况, 进行类型擦除情况
		 */
		int i = Erase.add(1,  2); // 参数都是Integer, 所以T为Integer类型
		Number f = Erase.add(1,  1.2); // 两个参数是一个Integer和float,所以取同一父类的最小级,为Number
		Object o = Erase.add(1, "abc"); // 两个参数是一个Integer和String,所以取同一父类的最小级,为Object
		
		/**
		 * 指定泛型时
		 */
		int a = Erase.<Integer>add(1, 2); // 指定了Integer,所以只能为Integer类型或者其子类
//		int b = Erase.<Integer>add(1, 1.2); // 指定了Integer,类型参数只能时integer不能有float
		Number c = Erase.<Number>add(1, 1.2); // 指定了Number,参数类型可以是integer和float,因为他们的最小父类是Number
		
		test1();
		test2();
	}
	
	public static <T> T add(T x, T y) {
		System.out.println(x);
		System.out.println(y);
		return x;
	}
	
	public static void test1() {
		ArrayList<String> list1 = new ArrayList<>();
		list1.add("1"); // 编译成功
//		list1.add(1); // 编译报错
		String string = list1.get(0); // 返回类型就是String
		
		ArrayList list2 = new ArrayList<String>();
		list2.add("1"); // 编译通过
		list2.add(1); // 编译通过
		list2.get(0); // 类型就是Object
		
		new ArrayList<String>().add("11"); //编译通过
//		new ArrayList<String>().add(1); // 编译错误
	}
	
	/**
	 * 使用反射实现泛型的实列化
	 * @param <T>
	 * @param clazz
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static <T> T newTCalss(Class<T> clazz) throws InstantiationException, IllegalAccessException {
		T objT = clazz.newInstance();
		
		return objT;
	}
	
	public static void test2() {
//		List<String>[] list1 = new ArrayList<String>[10]; // 编译错误,非法创建
//		List<String>[] list2 = new ArrayList<?>[10]; // 编译错误,需要强转类型
		List<String>[] list3 = (List<String>[]) new ArrayList<?>[10]; // 可以创建,但是会出现警告
//		List<?>[] list4 = new ArrayList<String>[10]; // 编译错误,非法创建
		List<?>[] list5 = new ArrayList<?>[10]; // 可以创建
		List<String>[] list6 = new ArrayList[10]; // 可以创建但是会有警告
	}
}
