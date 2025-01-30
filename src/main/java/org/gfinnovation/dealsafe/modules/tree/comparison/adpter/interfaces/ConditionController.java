package org.gfinnovation.dealsafe.modules.tree.comparison.adpter.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.exception.models.layered.DomainException;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.dto.request.ComparisonCreationDTO;
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
public interface ConditionController {
    @Operation(summary = "Cadastro de uma operação de comparação", description = "Registra uma nova operação de comparação no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação de comparação criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("comparison")
    ResponseEntity<ComparisonSingular> createOperation(@RequestBody ComparisonCreationDTO request) throws DomainException, BadRequestException;
}
