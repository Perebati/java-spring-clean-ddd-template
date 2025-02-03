package org.gfinnovation.dealsafe.modules.tree.node.adapter.web.controller;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.exception.models.layered.DomainException;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.controller.interfaces.NodeTreeController;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationIfDTO;
import org.gfinnovation.dealsafe.modules.tree.node.application.usecase.command.*;
import org.gfinnovation.dealsafe.modules.tree.node.application.usecase.query.ReadNodeBlock;
import org.gfinnovation.dealsafe.modules.tree.node.application.usecase.query.ReadNodeIf;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class TreeController
 * @since 30/10/2024
 */

@Controller
class NodeTreeControllerImpl implements NodeTreeController {
    private final CreateNodeBlock createNodeBlock;
    private final CreateNodeBlockIf createNodeBlockIf;
    private final CreateNodeIf createNodeIf;
    private final CreateNodeIfIf createNodeIfIf;
    private final DeleteNodeBlock deleteNodeBlock;
    private final DeleteNodeif deleteNodeif;
    private final UpdateNodeBlock updateNodeBlock;
    private final UpdateNodeIf updateNodeIf;
    private final ReadNodeBlock readNodeBlock;
    private final ReadNodeIf readNodeIf;

    @Autowired
    NodeTreeControllerImpl(
            CreateNodeBlock createNodeBlock,
            CreateNodeBlockIf createNodeBlockIf,
            CreateNodeIf createNodeIf,
            CreateNodeIfIf createNodeIfIf,
            DeleteNodeBlock deleteNodeBlock,
            DeleteNodeif deleteNodeif,
            UpdateNodeBlock updateNodeBlock,
            UpdateNodeIf updateNodeIf,
            ReadNodeBlock readNodeBlock,
            ReadNodeIf readNodeIf
    ) {
        this.createNodeBlock = createNodeBlock;
        this.createNodeBlockIf = createNodeBlockIf;
        this.createNodeIf = createNodeIf;
        this.createNodeIfIf = createNodeIfIf;
        this.deleteNodeBlock = deleteNodeBlock;
        this.deleteNodeif = deleteNodeif;
        this.updateNodeBlock = updateNodeBlock;
        this.updateNodeIf = updateNodeIf;
        this.readNodeBlock = readNodeBlock;
        this.readNodeIf = readNodeIf;
    }


    @Override
    public ResponseEntity<Void> createNodeBlock(NodeCreationDTO request) throws DomainException, BadRequestException {
        this.createNodeBlock.execute(request);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> createNodeBlockIf(NodeCreationIfDTO request) throws DomainException, BadRequestException {
        this.createNodeBlockIf.execute(request);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> createNodeIf(NodeCreationDTO request) throws DomainException, BadRequestException {
        this.createNodeIf.execute(request);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> createNodeIfIf(NodeCreationIfDTO request) throws DomainException, BadRequestException {
        this.createNodeIfIf.execute(request);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<NodeTreeBlock> readNodeBlock(UUID request) throws DomainException, BadRequestException {
        return ResponseEntity.ok(this.readNodeBlock.execute(request));
    }

    @Override
    public ResponseEntity<NodeTreeIf> readNodeIf(UUID request) throws DomainException, BadRequestException {
        return ResponseEntity.ok(this.readNodeIf.execute(request));
    }

    @Override
    public ResponseEntity<NodeTreeBlock> updateNodeBlock(NodeTreeBlock request) throws DomainException, BadRequestException {
        return ResponseEntity.ok(this.updateNodeBlock.execute(request));
    }

    @Override
    public ResponseEntity<NodeTreeIf> updateNodeIf(NodeTreeIf request) throws DomainException, BadRequestException {
        return ResponseEntity.ok(this.updateNodeIf.execute(request));
    }

    @Override
    public ResponseEntity<Void> deleteNodeBlock(UUID request) throws DomainException, BadRequestException {
        return ResponseEntity.ok(this.deleteNodeBlock.execute(request));
    }

    @Override
    public ResponseEntity<Void> deleteNodeIf(UUID request) throws DomainException, BadRequestException {
        return ResponseEntity.ok(this.deleteNodeif.execute(request));
    }
}