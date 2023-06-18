package com.hl.javase.reflect;

/**
 * 
 * @author huanglin 2023/05/10 下午9:24:08
 *
 */
public class RefUser {

	private String name = "init";
	private int    age;
	
	public RefUser() {}
	
	private RefUser(String name, int age) {
		super();
		this.name = name;
		this.age  = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "User [name = " + name + ", age = " + age + "]";
	}
}