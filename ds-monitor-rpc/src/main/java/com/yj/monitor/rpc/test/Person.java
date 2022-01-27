package com.yj.monitor.rpc.test;

import java.io.Serializable;

/**
 * @Author gaolei
 * @Date 2022/1/26 下午3:42
 * @Version 1.0
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private int age;
    private String name;

    public Person() {
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
