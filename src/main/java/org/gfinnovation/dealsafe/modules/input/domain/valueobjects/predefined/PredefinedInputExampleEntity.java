package org.gfinnovation.dealsafe.modules.input.domain.valueobjects.predefined;

import lombok.Data;

/**
 * An example of what a predefined input can be.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class PredefinedInputExampleEntity
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

