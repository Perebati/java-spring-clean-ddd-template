package org.gfinnovation.dealsafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/*
TODO: Verificar a visibilidade de todas as classes criadas recentemente.
TODO: Adicionar comentários.
TODO: Os erros do Dealsafe não estão sendo disparados da maneira correta.
 */
@SpringBootApplication
@EnableAsync
public class DealSafeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DealSafeApplication.class, args);
    }

}
