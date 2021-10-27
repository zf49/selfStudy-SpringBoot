package com.zf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync     // main 方法开起async
public class Springboot08TaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot08TaskApplication.class, args);
    }

}
