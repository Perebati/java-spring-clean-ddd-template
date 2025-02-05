package org.gfinnovation.dealsafe.modules.input.adapter.web.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.exception.models.layered.DomainException;
import org.gfinnovation.dealsafe.modules.input.domain.Input;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface InputController
 * @since 04/11/2024
 */

@RestController
@RequestMapping("input")
@Tag(name = "Input")
@SecurityRequirement(name = "BearerAuth")
public interface InputController {
    @Operation(summary = "Cadastro de input dinâmico", description = "Registra um novo input no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Input criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping()
    ResponseEntity<Input> createInput(@RequestParam String name, @RequestBody String json) throws DomainException, BadRequestException;

    @Operation(summary = "Busca um input dinâmico", description = "Busca um input no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Input lido com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping()
    ResponseEntity<Input> readInput(UUID id) throws DomainException, BadRequestException;
}