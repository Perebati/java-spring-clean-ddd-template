package org.gfinnovation.dealsafe.modules.tree.node.adapter.web.controller;

import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.tree._shared.application.usecase.DeleteNodeUseCase;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.controller.interfaces.NodeTreeController;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationData;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeIfCreationData;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeTreeBlockData;
import org.gfinnovation.dealsafe.modules.tree.node.application.usecase.command.CreateNodeBlock;
import org.gfinnovation.dealsafe.modules.tree.node.application.usecase.command.CreateNodeIf;
import org.gfinnovation.dealsafe.modules.tree.node.application.usecase.command.UpdateNodeBlock;
import org.gfinnovation.dealsafe.modules.tree.node.application.usecase.query.FindNodeBlock;
import org.gfinnovation.dealsafe.modules.tree.node.application.usecase.query.FindNodeIf;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;

import java.util.Optional;
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
    private final UpdateNodeBlock updateNodeBlock;
    private final FindNodeBlock findNodeBlock;
    private final FindNodeIf findNodeIf;
    private final DeleteNodeUseCase deleteNodeUseCase;

    @Autowired
    NodeTreeControllerImpl(
            CreateNodeBlock createNodeBlock,
            CreateNodeIf createNodeIf,
            UpdateNodeBlock updateNodeBlock,
            FindNodeBlock findNodeBlock,
            FindNodeIf findNodeIf,
            DeleteNodeUseCase deleteNodeUseCase
    ) {
        this.createNodeBlock = createNodeBlock;
        this.createNodeIf = createNodeIf;
        this.updateNodeBlock = updateNodeBlock;
        this.findNodeBlock = findNodeBlock;
        this.findNodeIf = findNodeIf;
        this.deleteNodeUseCase = deleteNodeUseCase;
    }

    @Override
    public ResponseEntity<NodeTreeBlock> createNodeBlock(@NonNull NodeCreationData request) throws SystemGlobalException {
        return ResponseEntity.ok(this.createNodeBlock.execute(request));
    }

    @Override
    public ResponseEntity<NodeTreeIf> createNodeIf(@NonNull NodeIfCreationData request) throws SystemGlobalException {
        return ResponseEntity.ok(this.createNodeIf.execute(request));
    }

    @Override
    public ResponseEntity<Optional<NodeTreeBlock>> findNodeBlock(@NonNull UUID id) throws SystemGlobalException {
        return ResponseEntity.ok(this.findNodeBlock.execute(id));
    }

    @Override
    public ResponseEntity<Optional<NodeTreeIf>> findNodeIf(@NonNull UUID id) throws SystemGlobalException {
        return ResponseEntity.ok(this.findNodeIf.execute(id));
    }

    @Override
    public ResponseEntity<NodeTreeBlock> updateNodeBlock(@NonNull UUID id,
                                                         @NonNull NodeTreeBlockData request) throws SystemGlobalException {
        return ResponseEntity.ok(this.updateNodeBlock.execute(id, request));
    }

    @Override
    public ResponseEntity<Void> deleteNode(@NonNull UUID id) throws SystemGlobalException {
        this.deleteNodeUseCase.execute(id);

        return ResponseEntity.noContent().build();
    }
}