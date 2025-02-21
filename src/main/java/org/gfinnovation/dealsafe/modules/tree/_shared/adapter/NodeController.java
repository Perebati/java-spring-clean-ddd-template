package org.gfinnovation.dealsafe.modules.tree._shared.adapter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeController
 * @since v1.0 (21/02/2025)
 */

@RestController
@RequestMapping("tree/node")
@Tag(name = "Árvore - Geral")
@SecurityRequirement(name = "BearerAuth")
public interface NodeController {
    @Operation(
            summary = "Deleta de nó na árvore",
            description = "Realiza a deleção de um nó na árvore.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteNode(@PathVariable UUID id) throws SystemGlobalException;
}
