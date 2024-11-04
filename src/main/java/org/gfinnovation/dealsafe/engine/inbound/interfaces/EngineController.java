package org.gfinnovation.dealsafe.engine.inbound.interfaces;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface EngineController
 * @since 04/11/2024
 */
@RestController
@RequestMapping("engine")
@SecurityRequirement(name = "BearerAuth")
public interface EngineController {
    @Operation(summary = "Valida um json na árvore de validação", description = "Necessita do JSON em sí e a id do nó da árvore de validação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Json validado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping()
    ResponseEntity<Boolean> validateJson(@RequestParam UUID root_id, @RequestBody JsonNode jsonNode);
}
