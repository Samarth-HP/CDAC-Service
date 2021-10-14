package com.samagra.cdac;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CdacApplication {

    @Value("username") String username;
    @Value("senderId") String senderId;
    @Value("password") String password;

    public static void main(String[] args) {
        SpringApplication.run(CdacApplication.class, args);
    }

}
