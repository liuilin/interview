package com.liuilin.JUC;

import java.util.Arrays;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author liuqiang
 * @since 2021-08-02
 */
@Getter
@AllArgsConstructor
public enum CountryEnumEnum {
    UNKNOWN(0, "未知"),
    MALE(1, "男"),
    FEMALE(2, "女"),
    ;


    private final int code;
    private final String desc;

    public static String getDesc(Integer code) {
        return valueOf(Optional.ofNullable(code).orElse(0)).desc;
    }

    public static CountryEnumEnum valueOf(Integer code) {
        return Arrays.stream(values())
                .filter(v -> v.getCode() == code)
                .findFirst()
                .orElse(CountryEnumEnum.UNKNOWN);//.orElseThrow(() -> new IllegalArgumentException("No such enum object for the code : " + code));
    }

    public static boolean containsValue(Integer code) {
        return valueOf(code) != null;
    }

}
