package com.hl.javase.test;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

class C extends B {
	public static final String FOO = "bar";
	public void x() {}

	public void process() throws IOException {
		super.process();
		System.out.println("B,");
		throw new IOException();
	}
}
