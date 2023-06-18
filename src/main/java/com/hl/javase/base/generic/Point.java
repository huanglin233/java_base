package com.hl.javase.base.generic;

/**
 * 
 * @author huanglin 2023/04/01 下午6:12:02
 *
 */
public class Point<T, V> {
	
	private T key; // key的类型由T指定
	private V value; // value的类型由V指定

	public T getKey() {
		return key;
	}



	public void setKey(T key) {
		this.key = key;
	}



	public V getValue() {
		return value;
	}



	public void setValue(V value) {
		this.value = value;
	}



	public static void main(String[] args) {
		Point<String, Integer> point1 = new Point<>();
		point1.setKey("A");
		System.out.println(point1.getKey());
		
		Point<Integer, String> point2 = new Point<>();
		point2.setKey(1);
		System.out.println(point2.getKey());
	}
}
