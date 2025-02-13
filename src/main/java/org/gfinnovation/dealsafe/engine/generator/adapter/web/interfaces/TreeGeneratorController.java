package org.gfinnovation.dealsafe.engine.generator.adapter.web.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTree;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class TreeGeneratorController
 * @since v1.0 (13/02/2025)
 */

@RestController
@RequestMapping("engine")
@Tag(name = "Engine")
@SecurityRequirement(name = "BearerAuth")
public interface TreeGeneratorController {
    @Operation(summary = "Cria uma árvore de validação a partir de um json",
            description = "Necessita do JSON estruturado de acordo com a árvore de validação desejada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Árvore criada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @PostMapping("tree")
    ResponseEntity<RootTree<?>> createTree(@RequestBody RootTree<?> request);
}
