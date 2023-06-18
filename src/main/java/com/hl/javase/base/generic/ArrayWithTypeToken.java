package com.hl.javase.base.generic;

import java.lang.reflect.Array;

/**
 * 实例化泛型数组
 * @author huanglin 2023/04/17 下午10:09:57
 *
 */
public class ArrayWithTypeToken<T> {
	
	private T[] array;
	
	public ArrayWithTypeToken(Class<T> type, int size) {
		array = (T[]) Array.newInstance(type, size);
	}
	
	public void put(int index, T item) {
		array[index] = item;
	}
	
	public T get(int index) {
		return array[index];
	}
	
	public T[] create() {
		return array;
	}
	
	public static void main(String[] args) {
		ArrayWithTypeToken<Integer> aToken = new ArrayWithTypeToken<>(Integer.class, 10);
		Integer[] create = aToken.create();
	}
}
