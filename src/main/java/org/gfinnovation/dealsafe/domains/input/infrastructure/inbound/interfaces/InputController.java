package org.gfinnovation.dealsafe.domains.input.infrastructure.inbound.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.domains.input.entity.InputEntity;
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
@SecurityRequirement(name = "BearerAuth")
public interface InputController {
    @Operation(summary = "Cadastro de input dinâmico", description = "Registra um novo input no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Input criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping()
    ResponseEntity<InputEntity> createInput(@RequestParam String name, @RequestBody String json) throws FactoryException, ValidationException, RepositoryException, BusinessException;

    @Operation(summary = "Busca um input dinâmico", description = "Busca um input no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Input lido com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping()
    InputEntity readInput(@RequestParam UUID read_id) throws FactoryException, ValidationException, RepositoryException, BusinessException;
}


