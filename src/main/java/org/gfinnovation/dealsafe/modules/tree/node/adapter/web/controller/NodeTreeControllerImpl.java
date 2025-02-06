package org.gfinnovation.dealsafe.modules.tree.node.adapter.web.controller;

import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.controller.interfaces.NodeTreeController;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.node.application.usecase.command.*;
import org.gfinnovation.dealsafe.modules.tree.node.application.usecase.query.ReadNodeBlock;
import org.gfinnovation.dealsafe.modules.tree.node.application.usecase.query.ReadNodeIf;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeTreeControllerImpl
 * @since v1.0 (30/11/2024)
 */
@Controller
class NodeTreeControllerImpl implements NodeTreeController {
    private final CreateNodeBlock createNodeBlock;
    private final CreateNodeIf createNodeIf;
    private final DeleteNodeBlock deleteNodeBlock;
    private final DeleteNodeIf deleteNodeif;
    private final UpdateNodeBlock updateNodeBlock;
    private final UpdateNodeIf updateNodeIf;
    private final ReadNodeBlock readNodeBlock;
    private final ReadNodeIf readNodeIf;

    @Autowired
    NodeTreeControllerImpl(
            CreateNodeBlock createNodeBlock,
            CreateNodeIf createNodeIf,
            DeleteNodeBlock deleteNodeBlock,
            DeleteNodeIf deleteNodeif,
            UpdateNodeBlock updateNodeBlock,
            UpdateNodeIf updateNodeIf,
            ReadNodeBlock readNodeBlock,
            ReadNodeIf readNodeIf
    ) {
        this.createNodeBlock = createNodeBlock;
        this.createNodeIf = createNodeIf;
        this.deleteNodeBlock = deleteNodeBlock;
        this.deleteNodeif = deleteNodeif;
        this.updateNodeBlock = updateNodeBlock;
        this.updateNodeIf = updateNodeIf;
        this.readNodeBlock = readNodeBlock;
        this.readNodeIf = readNodeIf;
    }

    @Override
    public ResponseEntity<Void> createNodeBlock(@NonNull NodeCreationDTO request) throws SystemGlobalException {
        this.createNodeBlock.execute(request);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> createNodeIf(@NonNull NodeCreationDTO request) throws SystemGlobalException {
        this.createNodeIf.execute(request);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<NodeTreeBlock> readNodeBlock(@NonNull UUID request) throws SystemGlobalException {
        return ResponseEntity.ok(this.readNodeBlock.execute(request));
    }

    @Override
    public ResponseEntity<NodeTreeIf> readNodeIf(@NonNull UUID request) throws SystemGlobalException {
        return ResponseEntity.ok(this.readNodeIf.execute(request));
    }

    @Override
    public ResponseEntity<NodeTreeBlock> updateNodeBlock(@NonNull NodeTreeBlock request) throws SystemGlobalException {
        return ResponseEntity.ok(this.updateNodeBlock.execute(request));
    }

    @Override
    public ResponseEntity<NodeTreeIf> updateNodeIf(@NonNull NodeTreeIf request) throws SystemGlobalException {
        return ResponseEntity.ok(this.updateNodeIf.execute(request));
    }

    @Override
    public ResponseEntity<Void> deleteNodeBlock(@NonNull UUID request) throws SystemGlobalException {
        this.deleteNodeBlock.execute(request);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteNodeIf(@NonNull UUID request) throws SystemGlobalException {
        this.deleteNodeif.execute(request);
        return ResponseEntity.ok().build();
    }
}