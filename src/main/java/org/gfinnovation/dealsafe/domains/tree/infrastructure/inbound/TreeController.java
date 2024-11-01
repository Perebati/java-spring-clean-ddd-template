package org.gfinnovation.dealsafe.domains.tree.infrastructure.inbound;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.gfinnovation.dealsafe.domains.tree.application.business.interfaces.TreeBusiness;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeEntity;
import org.gfinnovation.dealsafe.domains.tree.infrastructure.inbound.dto.request.RootCreationDTO;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class TreeController
 * @since 30/10/2024
 */
@RestController
@RequestMapping("tree")
@SecurityRequirement(name = "BearerAuth")
public class TreeController {
    private final TreeBusiness treeBusiness;

    public TreeController(TreeBusiness treeBusiness) {
        this.treeBusiness = treeBusiness;
    }

    @Operation(summary = "Cadastro de nó de árvore", description = "Registra um novo nó raiz.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nó raiz criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/root/create")
    public ResponseEntity<RootTreeEntity> createRoot(@RequestBody RootCreationDTO request) {
        try {
            return ResponseEntity.ok(
                    this.treeBusiness.getRootTreeBusiness().create(
                            UUID.fromString(MDC.get("userId")),
                            UUID.fromString(MDC.get("companyId")),
                            request.getName(),
                            request.getType()
                    ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

