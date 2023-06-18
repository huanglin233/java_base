package com.hl.javase.base.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 
 * @author huanglin 2023/04/17 下午10:35:15
 *
 */
public class GenericType<T> {

	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public static void main(String[] args) {
		GenericType<String> gType = new GenericType<String>() {};
		Type superclass = gType.getClass().getGenericSuperclass();
		//getActualTypeArguments 返回确切的泛型参数, 如Map<String, Integer>返回[String, Integer]
		Type type = ((ParameterizedType) superclass).getActualTypeArguments()[0]; 
		System.out.println(type); // class java.lang.String
	}
}
