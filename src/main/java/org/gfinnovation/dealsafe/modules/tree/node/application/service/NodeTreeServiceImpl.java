package org.gfinnovation.dealsafe.modules.tree.node.application.service;

import org.gfinnovation.dealsafe._shared.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;
import org.gfinnovation.dealsafe.modules.input.domain.NodeInput;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeService;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeTreeRepository;
import org.springframework.stereotype.Service;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeTreeServiceImpl
 * @since v1.0 (10/02/2025)
 */

@Service
class NodeTreeServiceImpl<T extends NodeTree<NodeInput>>
        extends GenericServiceImpl<NodeTree<NodeInput>, NodeTreeRepository<T>>
        implements NodeTreeService<T> {

    protected NodeTreeServiceImpl(NodeTreeRepository<T> repository) {
        super(repository);
    }

    @Override
    public T createNode(T newNode,
                        Node<?> parent,
                        NodeTreeIf.SetNode nodeSet) throws SystemGlobalException {
        try {
            return this.repository.createNode(newNode, parent, nodeSet, getRepositoryAuth());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong creating a new node");
        }
    }
}