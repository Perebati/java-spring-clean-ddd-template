package org.gfinnovation.dealsafe.modules.tree.node.adapter.web.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.exception.models.layered.DomainException;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationIfDTO;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.springframework.http.ResponseEntity;
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
@Tag(name = "Árvore")
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
    ResponseEntity<Void> createNodeBlock(NodeCreationDTO request) throws DomainException, BadRequestException;

    @Operation(
            summary = "Cadastro de nó block na árvore",
            description = "Registra um novo nó bloco. Parent_id deve ser uma referencia para um nó IF, " +
                    "também deve ter o indicativo de posição na requisição.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nó criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/node-block-if")
    ResponseEntity<Void> createNodeBlockIf(NodeCreationIfDTO request) throws DomainException, BadRequestException;

    @Operation(
            summary = "Cadastro de nó IF na árvore",
            description = "Registra um novo nó bloco. Parent_id pode ser tanto uma referência para um Nó comum ou para um Root.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nó criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/node-if")
    ResponseEntity<Void> createNodeIf (NodeCreationDTO request) throws DomainException, BadRequestException;

    @Operation(
            summary = "Cadastro de nó IF na árvore",
            description = "Registra um novo nó bloco. Parent_id deve ser uma referencia para um nó IF, " +
                    "também deve ter o indicativo de posição na requisição.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nó criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/node-if-if")
    ResponseEntity<Void> createNodeIfIf (NodeCreationIfDTO request) throws DomainException, BadRequestException;

    @Operation(
            summary = "Leitura de nó block na árvore",
            description = "Realiza a leitura de um nó block na árvore.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/node-block")
    ResponseEntity<NodeTreeBlock> readNodeBlock (UUID request) throws DomainException, BadRequestException;

    @Operation(
            summary = "Leitura de nó IF na árvore",
            description = "Realiza a leitura de um nó IF na árvore.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/node-if")
    ResponseEntity<NodeTreeIf> readNodeIf (UUID request) throws DomainException, BadRequestException;

    @Operation(
            summary = "Update de nó block na árvore",
            description = "Realiza a atualização de um nó block na árvore.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/node-block")
    ResponseEntity<NodeTreeBlock> updateNodeBlock (NodeTreeBlock request) throws DomainException, BadRequestException;

    @Operation(
            summary = "Update de nó block na árvore",
            description = "Realiza a atualização de um nó block na árvore.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/node-if")
    ResponseEntity<NodeTreeIf> updateNodeIf (NodeTreeIf request) throws DomainException, BadRequestException;

    @Operation(
            summary = "Deleta de nó block na árvore",
            description = "Realiza a deleção de um nó block na árvore.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/node-block")
    ResponseEntity<Void> deleteNodeBlock (UUID request) throws DomainException, BadRequestException;

    @Operation(
            summary = "Deleta de nó IF na árvore",
            description = "Realiza a deleção de um nó IF na árvore.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/node-if")
    ResponseEntity<Void> deleteNodeIf (UUID request) throws DomainException, BadRequestException;
}
