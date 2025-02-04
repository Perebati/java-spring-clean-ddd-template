package org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.exception.models.layered.DomainException;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonCustomListRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonMultiRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonSingularRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonCustomList;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface ConditionController
 * @since 04/11/2024
 */

@RestController
@RequestMapping("tree/operation")
@Tag(name = "Operação")
@SecurityRequirement(name = "BearerAuth")
public interface ComparisonController {
    @Operation(summary = "Cadastro de uma operação de comparação simples",
            description = "Registra uma nova operação de comparação no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação de comparação criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("comparison-singular")
    ResponseEntity<ComparisonSingular> createSingularComparison(@RequestBody ComparisonSingularRecord request)
            throws DomainException, BadRequestException;

    @Operation(summary = "Cadastro de uma operação de comparação multípla",
            description = "Registra uma nova operação de comparação no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação de comparação criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("comparison-multi")
    ResponseEntity<ComparisonMulti> createMultiComparison(@RequestBody ComparisonMultiRecord request)
            throws DomainException, BadRequestException;

    @Operation(summary = "Cadastro de uma operação de BlackList",
            description = "Registra uma nova operação de Blacklist no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação de comparação criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("comparison-black-list")
    ResponseEntity<ComparisonCustomList> createBlackListComparison(@RequestBody ComparisonCustomListRecord request)
            throws DomainException, BadRequestException;

    @Operation(summary = "Cadastro de uma operação de WhiteList",
            description = "Registra uma nova operação de WhiteList no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação de comparação criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("comparison-white-list")
    ResponseEntity<ComparisonCustomList> createWhiteListComparison(@RequestBody ComparisonCustomListRecord request)
            throws DomainException, BadRequestException;
}
