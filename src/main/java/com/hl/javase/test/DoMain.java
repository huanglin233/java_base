package com.hl.javase.test;


import javafx.collections.transformation.SortedList;

import java.io.IOException;
import java.util.*;

public class DoMain extends Thread{
	 static void test() {
		 try {
			 String x = null;
			 System.out.println(x.toString());
		 } finally {
			 System.out.println("f");
		 }
	 }
	 int y = 12;
	 public void method(int y) {
		 y+=y;
		 System.out.println(y);
	 }

	 public Inners i = new Inners();
	public static void main(String[] args) throws InterruptedException {
		DoMain doMain = new DoMain();
		doMain.method(5);
	}
int x = 2;
	void wait23() throws InterruptedException {
		PriorityQueue pq = new PriorityQueue<>();
		pq.add("carrot");
		pq.add("apple");
		pq.add("banana");
		System.out.println(pq.poll() + " " + pq.peek());
	}
	public DoMain() {

	}

	@Override
	public void run() {
		x *= 2;
	}

	class Inners {
		 public int b;
		 public  int h;
	}

	public void s(int i, int j) {
		System.out.println(i + j);
	}
	public void s(int... x) {
		System.out.println("x");
	}
}
