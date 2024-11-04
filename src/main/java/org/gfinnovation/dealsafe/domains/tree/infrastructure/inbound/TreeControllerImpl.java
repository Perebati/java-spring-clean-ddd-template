package org.gfinnovation.dealsafe.domains.tree.infrastructure.inbound;

import org.gfinnovation.dealsafe.domains.tree.application.business.interfaces.TreeBusiness;
import org.gfinnovation.dealsafe.domains.tree.entity.NodeTreeEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeEntity;
import org.gfinnovation.dealsafe.domains.tree.infrastructure.inbound.dto.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.domains.tree.infrastructure.inbound.dto.request.RootCreationDynamicInputDTO;
import org.gfinnovation.dealsafe.domains.tree.infrastructure.inbound.dto.request.RootCreationPredefinedInputDTO;
import org.gfinnovation.dealsafe.domains.tree.infrastructure.inbound.interfaces.TreeController;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class TreeController
 * @since 30/10/2024
 */

@Controller
public class TreeControllerImpl implements TreeController {
    private final TreeBusiness treeBusiness;

    public TreeControllerImpl(TreeBusiness treeBusiness) {
        this.treeBusiness = treeBusiness;
    }

    @Override
    public ResponseEntity<RootTreeEntity> createRootPredefined(@RequestBody RootCreationPredefinedInputDTO request) {
        try {
            return ResponseEntity.ok(
                    this.treeBusiness.getRootTreeBusiness().create(
                            UUID.fromString(MDC.get("user_id")),
                            UUID.fromString(MDC.get("company_id")),
                            request.name(),
                            request.type()
                    ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<RootTreeEntity> createRootPredefined(RootCreationDynamicInputDTO request) {
        try {
            return ResponseEntity.ok(
                    this.treeBusiness.getRootTreeBusiness().create(
                            UUID.fromString(MDC.get("user_id")),
                            UUID.fromString(MDC.get("company_id")),
                            request.name(),
                            request.dynamicInput_id()
                    ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<NodeTreeEntity> createNode(NodeCreationDTO request) {
        try {
            return ResponseEntity.ok(
                    this.treeBusiness.getNodeTreeBusiness().create(
                            UUID.fromString(MDC.get("user_id")),
                            UUID.fromString(MDC.get("company_id")),
                            request.name(),
                            request.sequence(),
                            request.parent_id()
                    ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

