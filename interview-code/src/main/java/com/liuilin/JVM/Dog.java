package com.liuilin.JVM;

/**
 * @author Daniel Liu
 * @date 2022-09-09
 */
public class Dog extends AbstractAnimal {
    @Override
    public void eat() {
//        super.eat();
        System.out.println("Dog eat");
    }

    @Override
    void eat1() {
        System.out.println("dog eat1...");
    }
}
