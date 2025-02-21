package org.gfinnovation.dealsafe.modules.tree.node.adapter.web.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationData;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeIfCreationData;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeTreeBlockData;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeTreeController
 * @since v1.0 (04/11/2024)
 */
@RestController
@RequestMapping("tree/node")
@Tag(name = "Árvore - Nó")
@SecurityRequirement(name = "BearerAuth")
public interface NodeTreeController {
    @Operation(
            summary = "Cadastro de nó block na árvore",
            description = "Registra um novo nó bloco. Parent_id pode ser tanto uma referência para um Nó comum ou para um Root.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nó criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @PostMapping("/node-block")
    ResponseEntity<NodeTreeBlock> createNodeBlock(@RequestBody NodeCreationData request) throws SystemGlobalException;


    @Operation(
            summary = "Cadastro de nó IF na árvore",
            description = "Registra um novo nó bloco. Parent_id pode ser tanto uma referência para um Nó comum ou para um Root.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nó criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @PostMapping("/node-if")
    ResponseEntity<NodeTreeIf> createNodeIf(@RequestBody NodeIfCreationData request) throws SystemGlobalException;


    @Operation(
            summary = "Leitura de nó block na árvore",
            description = "Realiza a leitura de um nó block na árvore.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @GetMapping("/node-block/{id}")
    ResponseEntity<Optional<NodeTreeBlock>> findNodeBlock(@PathVariable UUID id) throws SystemGlobalException;

    @Operation(
            summary = "Leitura de nó IF na árvore",
            description = "Realiza a leitura de um nó IF na árvore.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @GetMapping("/node-if/{id}")
    ResponseEntity<Optional<NodeTreeIf>> findNodeIf(@PathVariable UUID id) throws SystemGlobalException;

    @Operation(
            summary = "Update de nó block na árvore",
            description = "Realiza a atualização de um nó block na árvore.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @PutMapping("/node-block/{id}")
    ResponseEntity<NodeTreeBlock> updateNodeBlock(@PathVariable UUID id,
                                                  @RequestBody NodeTreeBlockData request) throws SystemGlobalException;
}