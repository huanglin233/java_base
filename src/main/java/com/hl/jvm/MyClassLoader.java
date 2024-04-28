package com.hl.jvm;

import lombok.Data;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author huanglin
 * @date 2024/03/13 23:38
 */
@Data
public class MyClassLoader extends ClassLoader{

    private String root;

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = loadClassData(name);
        if(classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] loadClassData(String className){
        String fileName = root + File.separatorChar
                + className.replace('.', File.separatorChar) + ".class";
        try {
            InputStream           is   = new FileInputStream(fileName);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int    bufferSize = 1024;
            byte[] buffer     = new byte[bufferSize];
            int    length     = 0;
            while((length = is.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }

            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        MyClassLoader myClassLoader = new MyClassLoader();
        myClassLoader.setRoot("D:\\project\\java_base");
        Class<?> testClass = null;

        try {
            testClass = myClassLoader.loadClass("com.hl.jvm.Test");
            Object object = testClass.newInstance();
            System.out.println(object.getClass().getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
