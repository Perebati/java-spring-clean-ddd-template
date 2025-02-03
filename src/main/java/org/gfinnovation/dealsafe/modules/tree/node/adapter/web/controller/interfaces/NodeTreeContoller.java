package org.gfinnovation.dealsafe.modules.tree.node.adapter.web.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.exception.models.layered.DomainException;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeStatic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface TreeController
 * @since 04/11/2024
 */

@RestController
@RequestMapping("tree")
@Tag(name = "Árvore")
@SecurityRequirement(name = "BearerAuth")
public interface NodeTreeContoller {
    @Operation(
            summary = "Cadastro de nó de árvore",
            description = "Registra um novo nó comum. Parent_id pode ser tanto uma referência para um Nó comum ou para um Root.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nó criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/node")
    ResponseEntity<Void> createNode(NodeCreationDTO request) throws DomainException, BadRequestException;
}
