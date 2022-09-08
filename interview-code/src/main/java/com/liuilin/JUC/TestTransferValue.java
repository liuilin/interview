package com.liuilin.JUC;

import java.util.concurrent.locks.ReentrantLock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author liuqiang
 * @since 2021-08-02
 */
public class TestTransferValue {

    // age = 20
    // person.getName() = liu
    // str = Daniel
    public static void main(String[] args) {
        TestTransferValue test = new TestTransferValue();
        int age = 20;
        test.changeValue1(age);
        System.out.println("age = " + age); // 打印的是 main 方法数据

        Person person = new Person("lin");
        test.changeValue2(person);
        System.out.println("person.getName() = " + person.getName()); // 打印的是 main 方法数据

        String str = "Daniel";
        test.changeValue3(str);
        System.out.println("str = " + str); // 打印的是 main 方法数据
    }

    private void changeValue3(String str) {
        str = "xxx";
    }

    private void changeValue2(Person person) {
        person.setName("liu");
    }

    private void changeValue1(int age) {
        age = 30;
    }

}

@Getter
@Setter
@AllArgsConstructor
class Person {

    private String name;

}