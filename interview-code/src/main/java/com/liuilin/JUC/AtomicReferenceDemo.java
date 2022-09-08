package com.liuilin.JUC;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author liuqiang
 * @since 2021-08-02
 */
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        User lin = new User("Lin", 18);
        User liu = new User("Lin", 19);
        AtomicReference<User> atomicReference = new AtomicReference<>(lin);
//        atomicReference.set(lin);
        System.out.println(atomicReference.compareAndSet(lin, liu) + " --- " + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(lin, liu) + " --- " + atomicReference.get().toString());

        HashSet<Object> hashSet = new HashSet<>();
        hashSet.add(lin);
        hashSet.add(liu);
        System.out.println("hashSet = " + hashSet);
        System.out.println(lin==liu);
    }
}

@Getter
@Setter
@ToString
@AllArgsConstructor
class User {
    private String name;
    private Integer age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age.equals(user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age);
    }
}