package com.hl.javase.base.generic;

import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author huanglin 2023/04/01 下午6:56:57
 *
 */
public class InfoImpl<T> implements Info<T> {
	
		private T var;
		
		InfoImpl(T var) {
			this.var = var;
		}
		
		public void setVar(T var) {
			this.var = var;
		}

		@Override
		public T getVar() {
			
			return this.var;
		}
		
		public static void main(String[] args) {
			Info<String> info = null;
			info = new InfoImpl<String>("tom");
			
			System.out.println(info.getVar());
			fun1(info);
			fun2(info);
		}
		
		// 上限,表明参数可以是指定的类型或者此类型的子类
		public static void fun1(Info<? extends Object> temp) {
			System.out.println("上限: " + temp.getVar());
		}
		
		// 下限,表明参数可以指定的类型或此类型的父类
		public static void fun2(Info<? super String> temp) {
			System.out.println("下限: " + temp.getVar());
		}
		
		private  <T extends Comparable<? super T>> T max(List<? extends T> e1) {
			if(e1 == null) {
				return null;
			}
			
			// 迭代器返回元素属于E的某个子类型
			Iterator<? extends T> iterator = e1.iterator();
			T res = iterator.next();
			while(iterator.hasNext()) {
				T next = iterator.next();
				if(next.compareTo(res) > 0) {
					return next;
				}
 			}
			
			return res;
		} 
}