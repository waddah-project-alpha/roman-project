package com.adobe.integertoroman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class IntegerToRomanApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegerToRomanApplication.class, args);
	}

}
