package com.greatestsasha.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(proxyBeanMethods = false)
public class Main {

    /**
     * Can the non-blocking driver work well with normal spring-data-jpa?
     * <p>
     * Spring Data JPA and JPA in general use blocking APIs to operate the driver.
     * That being said, there's no way to benefit from non-blocking drivers using JPA. Virtual threads however,
     * provide a non-blocking-like experience by switching threads that would otherwise wait for I/O to happen.
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}