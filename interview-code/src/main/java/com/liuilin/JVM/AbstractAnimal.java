package com.liuilin.JVM;

/**
 * @author Daniel Liu
 * @date 2022-09-09
 */
abstract class AbstractAnimal {
    public void eat() {
        System.out.println("animal eat");
    }

    abstract void eat1();
}
