package com.hl.javase.base.annotation;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author huanglin 2023/04/27 下午10:29:07
 *
 */
public class TestB extends TestA {

	
	/**
	 * 重写父类方法
	 */
	@Override
	public void test() {
	
	}
	
	/**
	 * 表示方法被弃用
	 */
	@Deprecated
	public void oldTest() {
		
	}
	
	/**
	 * 忽略警告
	 */
	@SuppressWarnings("rawtypes")
	public List warningTest() {
		List list = new ArrayList<>();
		
		return list;
	}
}
