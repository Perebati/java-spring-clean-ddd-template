package org.gfinnovation.dealsafe.modules.input.domain.predefined;

import lombok.Data;

/**
 * An example of what a predefined input can be.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class PredefinedInputExampleEntity
 * @since v1.0 (30/11/2024)
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

