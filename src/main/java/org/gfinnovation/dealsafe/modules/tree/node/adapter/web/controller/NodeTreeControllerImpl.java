package org.gfinnovation.dealsafe.modules.tree.node.adapter.web.controller;

import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.controller.interfaces.NodeTreeController;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationData;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeTreeBlockData;
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
    private final ReadNodeBlock readNodeBlock;
    private final ReadNodeIf readNodeIf;

    @Autowired
    NodeTreeControllerImpl(
            CreateNodeBlock createNodeBlock,
            CreateNodeIf createNodeIf,
            DeleteNodeBlock deleteNodeBlock,
            DeleteNodeIf deleteNodeif,
            UpdateNodeBlock updateNodeBlock,
            ReadNodeBlock readNodeBlock,
            ReadNodeIf readNodeIf
    ) {
        this.createNodeBlock = createNodeBlock;
        this.createNodeIf = createNodeIf;
        this.deleteNodeBlock = deleteNodeBlock;
        this.deleteNodeif = deleteNodeif;
        this.updateNodeBlock = updateNodeBlock;
        this.readNodeBlock = readNodeBlock;
        this.readNodeIf = readNodeIf;
    }

    @Override
    public ResponseEntity<NodeTreeBlock> createNodeBlock(@NonNull NodeCreationData request) throws SystemGlobalException {
        return ResponseEntity.ok(this.createNodeBlock.execute(request));
    }

    @Override
    public ResponseEntity<NodeTreeIf> createNodeIf(@NonNull NodeCreationData request) throws SystemGlobalException {
        return ResponseEntity.ok(this.createNodeIf.execute(request));
    }

    @Override
    public ResponseEntity<NodeTreeBlock> readNodeBlock(@NonNull UUID id) throws SystemGlobalException {
        return ResponseEntity.ok(this.readNodeBlock.execute(id));
    }

    @Override
    public ResponseEntity<NodeTreeIf> readNodeIf(@NonNull UUID id) throws SystemGlobalException {
        return ResponseEntity.ok(this.readNodeIf.execute(id));
    }

    @Override
    public ResponseEntity<NodeTreeBlock> updateNodeBlock(@NonNull UUID id,
                                                         @NonNull NodeTreeBlockData request) throws SystemGlobalException {
        return ResponseEntity.ok(this.updateNodeBlock.execute(id, request));
    }

    @Override
    public ResponseEntity<Void> deleteNodeBlock(@NonNull UUID id) throws SystemGlobalException {
        this.deleteNodeBlock.execute(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteNodeIf(@NonNull UUID id) throws SystemGlobalException {
        this.deleteNodeif.execute(id);
        return ResponseEntity.ok().build();
    }
}