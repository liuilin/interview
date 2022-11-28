package com.liuilin.spring.constructorInjection;

import org.springframework.stereotype.Component;

/**
 * @author Daniel Liu
 * @date 2022-10-20
 */
@Component
public class ServiceA {
    private ServiceB serviceB;

    public ServiceA(ServiceB serviceB) {
        this.serviceB = serviceB;
    }
}
