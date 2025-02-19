package org.gfinnovation.dealsafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/*
TODO: Adotar flyway quando o projeto for pra produção.
TODO: Todo erro deve ter 'e'.
TODO: Fazer com que todos os services das classes que estendam Node herdam de NodeService.
 */
@SpringBootApplication
@EnableAsync
public class DealSafeApplication {
    public static void main(String[] args) {
        SpringApplication.run(DealSafeApplication.class, args);
    }
}