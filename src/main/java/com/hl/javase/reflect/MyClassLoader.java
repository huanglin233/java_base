package com.hl.javase.reflect;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author huanglin 2023/05/05 下午10:50:51
 *
 */
public class MyClassLoader extends ClassLoader{

	private String root;
	
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] classData = loadClassData(name);
		if(classData == null) {
			throw new ClassNotFoundException();
		} else {
			return defineClass(name, classData, 0, classData.length);
		}
	}
	
	private byte[] loadClassData(String className) {
		String fileName = root + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
		try {
			try (InputStream ins = new FileInputStream(fileName)) {
				ByteArrayOutputStream baos       = new ByteArrayOutputStream();
				int                   bufferSize = 1024;
				byte[]                buffer     = new byte[bufferSize];
				int                   length     = 0;
				while((length = ins.read(buffer)) != -1) {
					baos.write(buffer, 0, length);
				}
				return baos.toByteArray();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}
	
	public static void main(String[] args) {
		MyClassLoader classLoader = new MyClassLoader();
		classLoader.setRoot("D:\\temp");
		Class<?> testClass = null;
		try {
			testClass = classLoader.loadClass("com.hl.javase.Test2");
			Object obj = testClass.newInstance();
			System.out.println(obj.getClass().getClassLoader());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
