package com.hl.javase.reflect;

import com.hl.javase.reflect.test.Dog;

import java.lang.reflect.Field;

/**
 * @author huanglin
 * @date 2023/05/11 22:31
 */
public class ReflectField {

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.hl.javase.reflect.test.Dog");
        // 获取指定字段名称的Field类,注意字段修饰符必须为public而且存在改字段, 否则抛出NoSuchFieldException
        Field field = clazz.getField("mAnimalPublic");
        System.out.println("field: " + field);
        /*
            field: public int com.hl.javase.reflect.test.Animal.mAnimalPublic
        */
        // 获取所有修饰符为public的字段,包含父类字段
        Field [] fields = clazz.getFields();
        for(Field f : fields) {
            System.out.println("field: " + f);
        }
        /*
        field: public int com.hl.javase.reflect.test.Dog.mDogPublic
        field: public static int com.hl.javase.reflect.test.Dog.sDogPublic
        field: public int com.hl.javase.reflect.test.Animal.mAnimalPublic
        field: public static int com.hl.javase.reflect.test.Animal.sAnimalPublic
        field: public int com.hl.javase.reflect.test.Cell.mCellPublic
        */

        // 获取当前类所字段(包含private字段),注意不包含父类的字段
        System.out.println("================getDeclaredFields====================");
        Field[] fields1 = clazz.getDeclaredFields();
        for(Field f : fields1) {
            System.out.println("field1: " + f);
        }
        /*
         field1: private int com.hl.javase.reflect.test.Dog.mDogPrivate
         field1: public int com.hl.javase.reflect.test.Dog.mDogPublic
         field1: protected int com.hl.javase.reflect.test.Dog.mDogProtected
         field1: private int com.hl.javase.reflect.test.Dog.mDogDefault
         field1: private static int com.hl.javase.reflect.test.Dog.sDogPrivate
         field1: protected static int com.hl.javase.reflect.test.Dog.sDogProtected
         field1: public static int com.hl.javase.reflect.test.Dog.sDogPublic
         field1: static int com.hl.javase.reflect.test.Dog.sDogDefault
         */

        //获取指定字段名称的Field类,可以是任意修饰符的自动,注意不包含父类的字段
        Field field2 = clazz.getDeclaredField("mDogPrivate");
        System.out.println("field2: " + field2);
        /*
          field2: private int com.hl.javase.reflect.test.Dog.mDogPrivate
         */

        Dog dog = (Dog) clazz.newInstance();
        // 获取父类public字段并赋值
        Field mAnimalPublic = clazz.getField("mAnimalPublic");
        mAnimalPublic.set(dog, 11);

        // 只获取当前了类的字段,不获取父类的字段
        Field mDogPublic = clazz.getDeclaredField("mDogPublic");
        mDogPublic.set(dog, 12);
        Field mDogPrivate = clazz.getDeclaredField("mDogPrivate");
        // 设置可访问
        mDogPrivate.setAccessible(true);
        mDogPrivate.set(dog, 13);
        System.out.println(mAnimalPublic.get(dog) + "_" + dog.getmDogPublic() + "_" + dog.getmDogPrivate());
        /*
         * 11_12_13
         */

    }
}
