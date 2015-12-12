package com.thoughtworks.jimmy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class SpringBootWebApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootWebApplication.class, args);
        String[] names = applicationContext.getBeanDefinitionNames();

        Arrays.sort(names);

        System.out.println("applicationContext via Spring Boot:");
        for (String name : names) {
            System.out.println(name);
        }
    }
}
