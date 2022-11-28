package com.liuilin.spring.constructorInjection;

import org.springframework.stereotype.Component;

/**
 * @author Daniel Liu
 * @date 2022-10-20
 */
@Component
public class ServiceB {
    private ServiceA serviceA;

    public ServiceB(ServiceA serviceA) {
        this.serviceA = serviceA;
    }
}
