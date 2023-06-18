package com.hl.javase.collection;

import java.util.Objects;

/**
 * 重写compareTo方法实现按年龄来排序
 *
 * @author huanglin by 2021/5/15
 */
public class MyCompareTo implements Comparable<MyCompareTo>{
    String  name;
    Integer age;

    public MyCompareTo(String name, Integer age) {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 重写compareTo方法实现按年龄来排序
     */
    @Override
    public int compareTo(MyCompareTo o) {
        return this.age.compareTo(o.getAge());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MyCompareTo that = (MyCompareTo) o;

        if (!Objects.equals(name, that.name)) {
            return false;
        }
        return Objects.equals(age, that.age);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }
}