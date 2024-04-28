package com.hl.javase.java8;

import lombok.Data;
import lombok.NonNull;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * java8 lambda 表达式
 * @author huanglin
 * @date 2024/03/07 23:48
 */
public class LambdaDo {

    @Data
    class Person {
        int    age;
        String name;

        public Person setAge(int age) {
            this.age = age;
            return this;
        }

        public Person setName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public String toString(){
            return this.age + ":" + this.name;
        }
    }

    @Test
    public void match() {
        List<String> list = Arrays.asList("aa", "bb", "cc");

        boolean a = list.stream().anyMatch(s -> s.startsWith("a"));
        System.out.println(a);

        boolean a1 = list.stream().allMatch(s -> s.startsWith("a"));
        System.out.println(a1);

        boolean a2 = list.stream().noneMatch(s -> s.startsWith("a"));
        System.out.println(a2);
    }

    @Test
    public void maxWithMin() {
        List<Person> list = new ArrayList<>();
        list.add(new Person().setAge(1).setName("aa1"));
        list.add(new Person().setAge(2).setName("aa2"));
        list.add(new Person().setAge(4).setName("aa4"));
        list.add(new Person().setAge(3).setName("aa3"));

        Person person = list.stream().max(Comparator.comparing(Person::getAge)).get();
        System.out.println(person.toString());

        Person person1 = list.stream().min(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if (o1.getAge() > o2.getAge()) return 1;
                if (o1.getAge() < o2.getAge()) return -1;
                return 0;
            }
        }).get();
        System.out.println(person1.toString());
    }

    @Test
    public void summaryStatistics() {
        List<Integer>        list  = Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10);
        IntSummaryStatistics stats = list.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println(stats.getMax());
        System.out.println(stats.getMin());
        System.out.println(stats.getSum());
        System.out.println(stats.getAverage());
        System.out.println(stats.getCount());
    }

    @Test
    public void peek() {
        List<Person> list = new ArrayList<>();
        list.add(new Person().setAge(1).setName("aa1"));
        list.add(new Person().setAge(2).setName("aa2"));
        list.add(new Person().setAge(4).setName("aa4"));
        list.add(new Person().setAge(3).setName("aa3"));

        System.out.println(list);
        List<Person> list2 = list.stream().filter(f -> f.getName().startsWith("aa1")).peek(t -> {
            System.out.println(t.getName());
        }).collect(Collectors.toList());
        System.out.println(list2);
    }

    @Test
    public void functionInterface() {
        IMyInterface myInterface = () -> System.out.println("haha233");
        myInterface.print();
    }

    @Test
    public void consumer() {
        Consumer<Person> consumer = (p) -> System.out.println("hello " + p.getName());
        consumer.accept(new Person().setName("aaa"));
    }

    @Test
    public void predicate() {
        Predicate<String> predicate = s -> s.length() > 0;
        System.out.println(predicate.test("aaa"));
        System.out.println(predicate.negate().test("aaa"));

        Predicate<String> isEmpty    = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();

        @NonNull String aa = "aa";
        System.out.println(isEmpty.test(aa));
        System.out.println(isNotEmpty.test(aa));
    }
}
