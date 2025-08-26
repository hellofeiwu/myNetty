package com.imooc.netty;

import java.lang.reflect.Method;

public class Refelction {

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.imooc.netty.Person");
        Object p = clazz.getConstructor().newInstance();
        Method setName = clazz.getMethod("setName", String.class);
        setName.invoke(p,"Tom");
        Method sayHello = clazz.getMethod("sayHello");

        Method[] methods = clazz.getMethods();
        for(Method m : methods){
            if(m.isAnnotationPresent(RunTimes.class)){
                RunTimes annotation = m.getAnnotation(RunTimes.class);
                int times= annotation.times();
                System.out.println("found Runtimes, will run "+ times +" times");
                for (int i=0;i<times;i++) {
                    m.invoke(p);
                }
            }
        }

        sayHello.invoke(p);
    }
}

class Person {
    public Person(){}
    private String name;
    private int age;

    public void sayHello(){
        System.out.println("Hello I am " + name);
    }

    @RunTimes(times = 2)
    public void jump(){
        System.out.println("I am jumping");
    }

    @RunTimes(times = 3)
    public void laugh(){
        System.out.println("I am laughing");
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
