package com.liuilin.spring.setInjection;

import org.springframework.stereotype.Component;

/**
 * @author Daniel Liu
 * @date 2022-10-20
 */
@Component
public class ServiceAA {
    private ServiceBB serviceBB;

    public void setServiceBB(ServiceBB serviceBB) {
        this.serviceBB = serviceBB;
        System.out.println("A 里面设置了 B");
    }
}
