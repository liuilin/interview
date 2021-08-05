package com.liuilin.JVM.ClassLoader;

/**
 * @author liuqiang
 * @since 2021-08-05
 */
public class CockerSpaniel extends Dog implements Friendly{
    @Override
    protected void finalize() { }
    public void eat(){ }
    @Override
    public void sayHello() { }
    @Override
    public void sayGoodbye() { }

    public static void main(String[] args) {
        System.out.println(new CockerSpaniel().toString());
    }
}

class Dog {
    public void sayHello(){ }

    @Override
    public String toString() {
        return "Dog";
    }
}

interface Friendly {
    void sayHello();
    void sayGoodbye();
}
