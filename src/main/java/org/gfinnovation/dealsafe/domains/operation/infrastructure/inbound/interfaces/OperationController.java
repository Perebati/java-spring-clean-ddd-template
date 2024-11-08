package org.gfinnovation.dealsafe.domains.operation.infrastructure.inbound.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.DomainException;
import org.gfinnovation.dealsafe.domains.operation.entity.ComparisonOperationEntity;
import org.gfinnovation.dealsafe.domains.operation.infrastructure.inbound.dto.request.OperationCreationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface OperationController
 * @since 04/11/2024
 */

@RestController
@RequestMapping("tree/operation")
@SecurityRequirement(name = "BearerAuth")
public interface OperationController {
    @Operation(summary = "Cadastro de uma operação de comparação", description = "Registra uma nova operação de comparação no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação de comparação criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("comparison")
    ResponseEntity<ComparisonOperationEntity> createOperation(@RequestBody OperationCreationDTO request) throws DomainException, BadRequestException;
}
