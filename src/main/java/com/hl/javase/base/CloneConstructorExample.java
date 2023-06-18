package com.hl.javase.base;

/**
 * clone()的替代方案
 * @author huanglin 2023/04/01 下午4:28:27
 *
 */
public class CloneConstructorExample {
	
	private int[] arr;
	
	public CloneConstructorExample() {
		arr = new int[10];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
	}
	
	public CloneConstructorExample(CloneConstructorExample example) {
		arr = new int[example.arr.length];
		for(int i = 0; i < example.arr.length; i++) {
			arr[i] = example.arr[i];
		}
	}
	
	public void set(int index, int value) {
		arr[index] = value;
	}
	
	public int get(int index) {
		return arr[index];
	}
	
	public static void main(String[] args) {
		CloneConstructorExample e1 = new CloneConstructorExample();
		CloneConstructorExample e2 = new CloneConstructorExample(e1);
		e1.set(2, 222);
		System.out.println(e1.get(2));
		System.out.println(e2.get(2));
	}
}
