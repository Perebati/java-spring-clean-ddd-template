package org.gfinnovation.dealsafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/*
TODO: Adicionar comentários.
TODO: Tornar check um read que retorna exception, e o read que retorna um Optional.
TODO: Documentar melhor a API. Definir quais argumentos são obrigatórios ou não. Dar exemplos de input e output. Fazer uso de PathVariable.
TODO: Fazer alguns testes unitários de casos reais.
TODO: Adotar flyway quando o projeto for pra produção.
TODO: Todo erro deve ter 'e'.
 */
@SpringBootApplication
@EnableAsync
public class DealSafeApplication {
    public static void main(String[] args) {
        SpringApplication.run(DealSafeApplication.class, args);
    }
}