package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.adapter.web.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.adapter.web.request.CompanyListData;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("dealboard")
@Tag(name = "Dealboard")
@SecurityRequirement(name = "BearerAuth")
public interface CompanyListController {
    @Operation(summary = "Cria uma lista de validação de empresas baseados no cnpj",
            description = "Necessita do nome e lista de cnpj's das empresas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @PostMapping("company-list")
    ResponseEntity<CompanyList> createList(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Dados para criação de uma lista de empresas",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CompanyListData.class),
                    examples = {
                            @ExampleObject(
                                    name = "CreateInputRecordRecordExemplo",
                                    value = """
                                            {
                                              "name": "Lista personaliza 01",
                                              "cnpjs": [
                                                "73486838000100", "78813083000125", "53617500000133", "85536411000159"
                                              ]
                                            }
                                            """
                            )
                    }
            )
    ) CompanyListData companyListData) throws SystemGlobalException;

    @Operation(summary = "Realiza a leitura de um lista de empresas",
            description = "Necessita do id da lista de empresas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @GetMapping("company-list/{id}")
    ResponseEntity<CompanyList> readList(@PathVariable UUID id) throws SystemGlobalException;

    @Operation(summary = "Realiza a leitura de todas as listas de empresas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @GetMapping("company-list/getAll")
    ResponseEntity<List<CompanyList>> realAllList() throws SystemGlobalException;

    @Operation(summary = "Atualiza um objeto de lista de empresas", description = "Necessita do objeto a ser atualizado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @PutMapping("company-list/{id}")
    ResponseEntity<CompanyList> updateList(@PathVariable UUID id, @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Dados para ataulização de uma lista de empresas",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CompanyListData.class),
                    examples = {
                            @ExampleObject(
                                    name = "UpdateInputRecordRecordExemplo",
                                    value = """
                                            {
                                              "name": "Lista personaliza 01 atualizada",
                                              "cnpjs": [
                                                "73486838000100", "78813083000125", "53617500000133", "85536411000159"
                                              ]
                                            }
                                            """
                            )
                    }
            )
    ) CompanyListData companyList) throws SystemGlobalException;
}