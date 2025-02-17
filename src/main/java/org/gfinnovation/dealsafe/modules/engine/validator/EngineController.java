package org.gfinnovation.dealsafe.modules.engine.validator;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class EngineController
 * @since v1.0 (04/11/2024)
 */

@RestController
@RequestMapping("engine")
@Tag(name = "Engine")
@SecurityRequirement(name = "BearerAuth")
public interface EngineController {
    @Operation(summary = "Valida um json na árvore de validação", description = "Necessita do JSON em sí e a id do nó da árvore de validação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Json validado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @PostMapping("{id}")
    ResponseEntity<Boolean> validateJson(@PathVariable UUID id, @RequestBody JsonNode jsonNode) throws SystemGlobalException;
}
