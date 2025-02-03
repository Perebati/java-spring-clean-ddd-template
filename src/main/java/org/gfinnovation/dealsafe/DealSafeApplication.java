package org.gfinnovation.dealsafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/*
TODO: Verificar a visibilidade de todas as classes criadas recentemente.
TODO: Adicionar comentários.
TODO: Task: Generalizar inclusão de novos nós em nós ordinários e especiais (IF).
TODO: Modificar a lógica de diferenciação entre aqueles nós que vão dentro de nós comuns e nós especiais (IF).
 */

@SpringBootApplication
@EnableAsync
public class DealSafeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DealSafeApplication.class, args);
    }

}
