package org.gfinnovation.dealsafe.modules.tree.root.adapter.web.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.exception.models.layered.DomainException;
import org.gfinnovation.dealsafe.modules.tree.root.adapter.web.request.RootCreationDynamicInputDTO;
import org.gfinnovation.dealsafe.modules.tree.root.adapter.web.request.RootCreationPredefinedInputDTO;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeStatic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface TreeController
 * @since 04/11/2024
 */

@RestController
@RequestMapping("tree")
@Tag(name = "Árvore - Raiz")
@SecurityRequirement(name = "BearerAuth")
public interface RootTreeController {
    @Operation(
            summary = "Cadastro de root de árvore com input estático/predefinido",
            description = "Cria um nó raiz para input predefinidos no sistema, se deseja utilizar o input dinâmico, usar a rota /root/dynamic-input")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nó raiz criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/root/predefind-input")
    ResponseEntity<RootTreeStatic> createRootPredefined(@RequestBody RootCreationPredefinedInputDTO request) throws DomainException, BadRequestException;

    @Operation(
            summary = "Cadastro de root de árvore com input dinâmico",
            description = "Cria um nó raiz para input definidos pelos usuário do sistema, se deseja utilizar um input prédefinido, usar a rota /root/predefined-input")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nó raiz criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/root/dynamic-input")
    ResponseEntity<RootTreeDynamic> createRootDynamic(@RequestBody RootCreationDynamicInputDTO request) throws DomainException, BadRequestException;
}
