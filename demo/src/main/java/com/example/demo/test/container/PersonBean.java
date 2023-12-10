package com.example.demo.test.container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PersonBean {
    @Autowired
    private PersonBean1 bean1;

    @Value("${111:11}")
    private int a;

}
