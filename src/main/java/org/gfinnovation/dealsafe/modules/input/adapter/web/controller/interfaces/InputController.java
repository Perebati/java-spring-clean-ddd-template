package org.gfinnovation.dealsafe.modules.input.adapter.web.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.input.adapter.web.request.CreateInputData;
import org.gfinnovation.dealsafe.modules.input.domain.Input;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class InputController
 * @since v1.0 (04/11/2024)
 */

@RestController
@RequestMapping("input")
@Tag(name = "Input")
@SecurityRequirement(name = "BearerAuth")
public interface InputController {
    @Operation(
            summary = "Cadastro de input dinâmico",
            description = "Registra um novo input no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Input criado com sucesso!", content =
            @Content(mediaType = "application/json", schema = @Schema(implementation = Input.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @PostMapping()
    ResponseEntity<Input> createInput(@RequestBody(
            description = "Dados para criação de input",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CreateInputData.class),
                    examples = {
                            @ExampleObject(
                                    name = "CreateInputData",
                                    value = """
                                            {
                                              "name": "Exemplo de Nome",
                                              "json": {
                                                    "chave": "valor"
                                                }
                                            }
                                            """
                            )
                    }
            )
    ) CreateInputData request)
            throws SystemGlobalException;

    @Operation(summary = "Busca um input dinâmico", description = "Busca um input no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Input lido com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @GetMapping("/{id}")
    ResponseEntity<Optional<Input>> readInput(@PathVariable UUID id) throws SystemGlobalException;
}