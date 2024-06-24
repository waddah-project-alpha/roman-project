package com.adobe.integertoroman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Spring Boot application to convert an integer to a roman repsentation. It uses Spring AspectJ,
 * Prometheus and other tools to handle different aspects of the app. This is the class that starts it all!
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class IntegerToRomanApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegerToRomanApplication.class, args);
    }

}
