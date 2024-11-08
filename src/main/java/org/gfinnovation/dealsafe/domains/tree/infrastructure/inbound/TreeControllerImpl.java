package org.gfinnovation.dealsafe.domains.tree.infrastructure.inbound;

import org.gfinnovation.dealsafe.configuration.exception.models.layered.DomainException;
import org.gfinnovation.dealsafe.domains.tree.application.business.interfaces.TreeBusiness;
import org.gfinnovation.dealsafe.domains.tree.entity.NodeTreeEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeDynamicEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeStaticEntity;
import org.gfinnovation.dealsafe.domains.tree.infrastructure.inbound.dto.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.domains.tree.infrastructure.inbound.dto.request.RootCreationDynamicInputDTO;
import org.gfinnovation.dealsafe.domains.tree.infrastructure.inbound.dto.request.RootCreationPredefinedInputDTO;
import org.gfinnovation.dealsafe.domains.tree.infrastructure.inbound.interfaces.TreeController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.concurrent.CompletableFuture;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class TreeController
 * @since 30/10/2024
 */

@Controller
class TreeControllerImpl implements TreeController {
    private final TreeBusiness treeBusiness;

    public TreeControllerImpl(TreeBusiness treeBusiness) {
        this.treeBusiness = treeBusiness;
    }

    @Override
    public ResponseEntity<CompletableFuture<RootTreeStaticEntity>> createRootPredefined(@RequestBody RootCreationPredefinedInputDTO request) throws DomainException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.treeBusiness.getRootTreeBusiness().create(
                            request.name(),
                            request.type()
                    ));
        } catch (DomainException e) {
            throw e;
        } catch (Exception e) {
            throw new DomainException("Controller: Unexpected error in static root creation.");
        }
    }

    @Override
    public ResponseEntity<CompletableFuture<RootTreeDynamicEntity>> createRootPredefined(RootCreationDynamicInputDTO request) throws DomainException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.treeBusiness.getRootTreeBusiness().create(
                            request.name(),
                            request.dynamicInput_id()
                    ));
        } catch (DomainException e) {
            throw e;
        } catch (Exception e) {
            throw new DomainException("Controller: Unexpected error in dynamic root creation.");
        }
    }

    @Override
    public ResponseEntity<NodeTreeEntity> createNode(NodeCreationDTO request) throws DomainException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.treeBusiness.getNodeTreeBusiness().create(
                            request.name(),
                            request.sequence(),
                            request.parent_id()
                    ));
        } catch (DomainException e) {
            throw e;
        } catch (Exception e) {
            throw new DomainException("Controller: Unexpected error in node creation.");
        }
    }
}

