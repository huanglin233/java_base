package com.hl.javase.test;

import java.io.IOException;
import java.util.List;

class B implements A {
	public static final String FOO = "foo";
	@Override
	public void x() {
		// TODO Auto-generated method stub
		
	}
	
	public void y() {}

	List tops;
	public final void add(String s) {
		tops.add(s);
	}
	public void process() throws IOException {
		System.out.println("A,");
	}
}