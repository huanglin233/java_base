package com.hl.javase;

import java.util.Objects;

/**
 * @author huanglin
 * @date 2021/5/30 上午10:21
 */
public class Person {
    String  name;
    int     age;
    boolean gender;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Person person = (Person) o;

        if (age != person.age) {
            return false;
        }
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + name.hashCode();
        hash = hash * 31 + age;

        return hash;
    }
}