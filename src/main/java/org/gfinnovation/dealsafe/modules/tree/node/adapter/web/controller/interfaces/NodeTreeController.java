package org.gfinnovation.dealsafe.modules.tree.node.adapter.web.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface TreeController
 * @since 04/11/2024
 */

@RestController
@RequestMapping("tree")
@Tag(name = "Árvore - Nó")
@SecurityRequirement(name = "BearerAuth")
public interface NodeTreeController {
    @Operation(
            summary = "Cadastro de nó block na árvore",
            description = "Registra um novo nó bloco. Parent_id pode ser tanto uma referência para um Nó comum ou para um Root.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nó criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/node-block")
    ResponseEntity<Void> createNodeBlock(@NonNull @RequestBody NodeCreationDTO request) throws SystemGlobalException;


    @Operation(
            summary = "Cadastro de nó IF na árvore",
            description = "Registra um novo nó bloco. Parent_id pode ser tanto uma referência para um Nó comum ou para um Root.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nó criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/node-if")
    ResponseEntity<Void> createNodeIf(@NonNull @RequestBody NodeCreationDTO request) throws SystemGlobalException;


    @Operation(
            summary = "Leitura de nó block na árvore",
            description = "Realiza a leitura de um nó block na árvore.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/node-block")
    ResponseEntity<NodeTreeBlock> readNodeBlock(@NonNull @RequestParam UUID request) throws SystemGlobalException;

    @Operation(
            summary = "Leitura de nó IF na árvore",
            description = "Realiza a leitura de um nó IF na árvore.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/node-if")
    ResponseEntity<NodeTreeIf> readNodeIf(@NonNull @RequestParam UUID request) throws SystemGlobalException;

    @Operation(
            summary = "Update de nó block na árvore",
            description = "Realiza a atualização de um nó block na árvore.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/node-block")
    ResponseEntity<NodeTreeBlock> updateNodeBlock(@NonNull @RequestBody NodeTreeBlock request) throws SystemGlobalException;

    @Operation(
            summary = "Update de nó block na árvore",
            description = "Realiza a atualização de um nó block na árvore.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/node-if")
    ResponseEntity<NodeTreeIf> updateNodeIf(@NonNull @RequestBody NodeTreeIf request) throws SystemGlobalException;

    @Operation(
            summary = "Deleta de nó block na árvore",
            description = "Realiza a deleção de um nó block na árvore.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/node-block")
    ResponseEntity<Void> deleteNodeBlock(@NonNull @RequestParam UUID request) throws SystemGlobalException;

    @Operation(
            summary = "Deleta de nó IF na árvore",
            description = "Realiza a deleção de um nó IF na árvore.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/node-if")
    ResponseEntity<Void> deleteNodeIf(@NonNull @RequestParam UUID request) throws SystemGlobalException;
}