package org.gfinnovation.dealsafe._sandbox.authtest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Test, don't bother.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class AuthResponse
 * @since 01/11/2024
 */

@Data
@AllArgsConstructor
@Schema(description = "Resposta contendo o token JWT")
public class AuthResponse {

    @Schema(description = "Token JWT para autenticação", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;
}