package com.hl.javase.thread.priorityBlockingQueue;

/**
 * 现实排序的对象
 *
 * @author huanglin by 2021/5/15
 */
public class Person implements Comparable<Person>{

    private int    id;
    private String name;

    public Person() {};

    public Person(int id, String name) {
        super();
        this.id   = id;
        this.name = name;
    }

    @Override
    public int compareTo(Person person) {
        return Integer.compare(this.id, person.getId());
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
