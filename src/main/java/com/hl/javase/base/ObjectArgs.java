package com.hl.javase.base;

/**
 * 
 * @author huanglin 2023/04/01 下午4:28:20
 *
 */
public class ObjectArgs {
	
	String name;
	
	ObjectArgs(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	String getObjectAddress() {
		return super.toString();
	}
	
	
	public static void main(String[] args) {
		ObjectArgs obj = new ObjectArgs("A");
		System.out.println(obj.getObjectAddress()); // com.hl.javase.base.ObjectArgs@3d012ddd
		fun1(obj);
		System.out.println(obj.getObjectAddress()); // com.hl.javase.base.ObjectArgs@3d012ddd
		System.out.println(obj.getName()); // A
		fun2(obj);
		System.out.println(obj.getObjectAddress()); // com.hl.javase.base.ObjectArgs@3d012ddd
		System.out.println(obj.getName()); // C
	}

 	private static void fun1(ObjectArgs obj) {
 			System.out.println(obj.getObjectAddress()); // com.hl.javase.base.ObjectArgs@3d012dd
 			obj = new ObjectArgs("B");
 			System.out.println(obj.getObjectAddress()); // com.hl.javase.base.ObjectArgs@626b2d4a
 			System.out.println(obj.getName()); // B
 	}
 	
 	private static void fun2(ObjectArgs obj) {
 		obj.setName("C");
 	}
}