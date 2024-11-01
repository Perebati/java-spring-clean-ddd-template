package org.gfinnovation.dealsafe.authentication.authtest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Test, don't bother.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class AuthRequest
 * @since 01/11/2024
 */

@Data
@Schema(description = "Requisição para registro ou login de usuário")
public class AuthRequest {

    @Schema(description = "Endereço de e-mail do usuário", example = "usuario@exemplo.com", required = true)
    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "O e-mail deve ser válido")
    private String email;
}