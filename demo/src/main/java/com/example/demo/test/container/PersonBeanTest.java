package com.example.demo.test.container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonBeanTest {

    @Autowired
    PersonBean1 personBean1;
}
