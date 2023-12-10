package com.example.demo.test.container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonBean1 {
    @Autowired
    public PersonBean personBean;
    public String name1;
}
