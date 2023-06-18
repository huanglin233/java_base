package com.hl.javase.base.annotation.processor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author huanglin 2023/04/29 下午9:44:28
 *
 */
@Target({ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.CLASS)
public @interface Factory {

	  /**
	   * 工厂的名字
	   */
	  Class type();

	  /**
	   * 用来表示生成哪个对象的唯一id
	   */
	  String id();
}
