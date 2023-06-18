package com.hl.javase.base;

/**
 * 
 * @author huanglin 2023/03/14 下午11:52:06
 *
 */
public class StringBase {
	
	public static void main(String[] args) {
		String s1 = new String("aaa");
		String s2 = new String("aaa");
		System.out.println(s1 == s2); // false
		String s3 = s1.intern();
		System.out.println(s3 == s1); // false
		System.out.println(s3 == s1.intern()); // true
		
		String s4 = "aaa";
		String s5 = "aaa";
		System.out.println(s4 == s5); // true
	}
}
