package org.gfinnovation.dealsafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DealSafeApplication {
    public static void main(String[] args) {
        SpringApplication.run(DealSafeApplication.class, args);
    }
}