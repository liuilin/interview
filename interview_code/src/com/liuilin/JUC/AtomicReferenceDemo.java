package com.liuilin.JUC;

import java.util.concurrent.atomic.AtomicReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author liuqiang
 * @since 2021-08-02
 */
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        User lin = new User("Lin", 18);
        User liu = new User("Liu", 24);
        AtomicReference<User> atomicReference = new AtomicReference<>(lin);
//        atomicReference.set(lin);
        System.out.println(atomicReference.compareAndSet(lin, liu) + " --- " + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(lin, liu) + " --- " + atomicReference.get().toString());
    }
}

@Getter
@Setter
@ToString
@AllArgsConstructor
class User {

    private String name;

    private Integer age;

}