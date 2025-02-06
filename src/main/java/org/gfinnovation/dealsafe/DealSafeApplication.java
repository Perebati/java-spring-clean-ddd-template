package org.gfinnovation.dealsafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/*
TODO: Verificar a visibilidade de todas as classes criadas recentemente.
TODO: Adicionar comentários.
TODO: Documentar melhor a API. Definir quais argumentos são obrigatórios ou não. Dar exemplos de input e output. Fazer uso de PathVariable.
TODO: Fazer alguns testes unitários de casos reais.
 */
@SpringBootApplication
@EnableAsync
public class DealSafeApplication {
    public static void main(String[] args) {
        SpringApplication.run(DealSafeApplication.class, args);
    }
}