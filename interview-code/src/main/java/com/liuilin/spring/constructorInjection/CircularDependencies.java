package com.liuilin.spring.constructorInjection;

/**
 * 循环依赖
 *
 * @author Daniel Liu
 * @date 2022-10-20
 */
public class CircularDependencies {
    public static void main(String[] args) {
//        new ServiceA(new ServiceB(new ServiceA()));
    }
}
