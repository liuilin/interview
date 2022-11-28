package com.liuilin.spring.setInjection;

import org.springframework.stereotype.Component;

/**
 * @author Daniel Liu
 * @date 2022-10-20
 */
@Component
public class ServiceBB {
    private ServiceAA serviceAA;

    public void setServiceAA(ServiceAA serviceAA) {
        this.serviceAA = serviceAA;
        System.out.println("B 里面设置了 A");
    }
}
