package com.liuilin.JVM.ClassLoader;

/**
 * 解析调用中的非虚方法
 *
 * @author liuqiang
 * @since 2021-08-05
 */
public class Son extends Father {

    public Son() {
        super();
    }

    public Son(int age) {
        this();
    }

    // 不是重写的父类的静态方法，因为静态方法不能被重写
    public static void showStatic(String str) {
        System.out.println("son " + str);
    }

    private void showPrivate(String str) {
        System.out.println("son private " + str);
    }

    public void show() {
        // =========================== 非虚方法 ===========================
        showStatic("good"); // invokestatic
        super.showStatic("nice"); // invokestatic
        showPrivate("Kimochi"); // invokestatic
        super.showNormalMethod(); // invokestatic
        showFinal(); // 虽然显示的是 invokevirtual，但因为被 final 修饰，不能被子类重写，所以也是非虚方法
        // =========================== 虚方法 ===========================
        showNormalMethod(); // invokevirtual，子类会重写父类方法，编译期确定不下来
        info(); // invokevirtual

        MethodInterface in = null;
        in.methodA(); // invokeinterface 要想运行成功，需要子类实现方法，重写时又不知子类是谁。所以表现为虚方法
    }

    private void info() { }

    public static void main(String[] args) {
        Son son = new Son();
        son.show();
    }
}

class Father {

    public Father() {
        System.out.println("father 的空参构造");
    }

    public static void showStatic(String str) {
        System.out.println("father " + str);
    }

    public final void showFinal() {
        System.out.println("father show final");
    }

    public void showNormalMethod() {
        System.out.println("father normal method");
    }
}

interface MethodInterface {
    void methodA();
}