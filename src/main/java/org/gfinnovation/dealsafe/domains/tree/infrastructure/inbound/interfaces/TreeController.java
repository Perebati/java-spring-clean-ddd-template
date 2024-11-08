package org.gfinnovation.dealsafe.domains.tree.infrastructure.inbound.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.DomainException;
import org.gfinnovation.dealsafe.domains.tree.entity.NodeTreeEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeDynamicEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeStaticEntity;
import org.gfinnovation.dealsafe.domains.tree.infrastructure.inbound.dto.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.domains.tree.infrastructure.inbound.dto.request.RootCreationDynamicInputDTO;
import org.gfinnovation.dealsafe.domains.tree.infrastructure.inbound.dto.request.RootCreationPredefinedInputDTO;
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
@SecurityRequirement(name = "BearerAuth")
public interface TreeController {
    @Operation(
            summary = "Cadastro de root de árvore com input estático/predefinido",
            description = "Cria um nó raiz para input predefinidos no sistema, se deseja utilizar o input dinâmico, usar a rota /root/dynamic-input")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nó raiz criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/root/predefind-input")
    ResponseEntity<CompletableFuture<RootTreeStaticEntity>> createRootPredefined(@RequestBody RootCreationPredefinedInputDTO request) throws DomainException, BadRequestException;

    @Operation(
            summary = "Cadastro de root de árvore com input dinâmico",
            description = "Cria um nó raiz para input definidos pelos usuário do sistema, se deseja utilizar um input prédefinido, usar a rota /root/predefined-input")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nó raiz criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/root/dynamic-input")
    ResponseEntity<CompletableFuture<RootTreeDynamicEntity>> createRootPredefined(@RequestBody RootCreationDynamicInputDTO request) throws DomainException, BadRequestException;

    @Operation(
            summary = "Cadastro de nó de árvore",
            description = "Registra um novo nó comum. Parent_id pode ser tanto uma referência para um Nó comum ou para um Root.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nó criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/node")
    ResponseEntity<NodeTreeEntity> createNode(NodeCreationDTO request) throws DomainException, BadRequestException;
}
