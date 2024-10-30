package org.gfinnovation.dealsafe.domains.input.entity.predefined.models;

import lombok.Data;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class PredefinedInputExampleEntity
 * @authorNote An example of what a predefined input can be.
 * @since 30/10/2024
 */
@Data
public class PredefinedInputExampleEntity {
    private String CPF;
    private Integer idade;
    private Endereco endereco;

    @Data
    static class Endereco {
        private String rua;
        private String bairro;
    }
}

