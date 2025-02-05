package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.adapter.web.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
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
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("company-list")
    ResponseEntity<CompanyList> createList(@RequestBody CompanyListData companyListData) throws SystemGlobalException;

    @Operation(summary = "Realiza a leitura de um lista de empresas",
            description = "Necessita do id da lista de empresas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("company-list")
    ResponseEntity<CompanyList> readList(@RequestParam UUID company_list) throws SystemGlobalException;

    @Operation(summary = "Realiza a leitura de todas as listas de empresas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("company-list/getAll")
    ResponseEntity<List<CompanyList>> realAllList() throws SystemGlobalException;

    @Operation(summary = "Atualiza um objeto de lista de empresas", description = "Necessita do objeto a ser atualizado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("company-list")
    ResponseEntity<CompanyList> updateList(@RequestBody CompanyList companyList) throws SystemGlobalException;

    @Operation(summary = "Deleta uma lista de empresas baseado no id!",
            description = "Necessita do id da lista de empresas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("company-list")
    ResponseEntity<Void> deleteList(@RequestParam UUID company_list) throws SystemGlobalException;
}