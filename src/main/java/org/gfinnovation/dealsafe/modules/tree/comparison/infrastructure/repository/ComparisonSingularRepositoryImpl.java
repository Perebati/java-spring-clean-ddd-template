package org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.gfinnovation.dealsafe._shared.modules.application.RepositoryAuth;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.repository.ComparisonSingularRepository;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.ComparisonSingularEntity;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.mapper.ComparisonSingularMapper;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.repository.NodeTreeBlockRepository;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.RootTreeStatic;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.repository.RootTreeDynamicRepository;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.repository.RootTreeRepository;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.repository.RootTreeStaticRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ComparisonOperationRepositoryImpl
 * @since 30/10/2024
 */
@Component
class ComparisonSingularRepositoryImpl
        extends GenericBusinessRepositoryImpl<ComparisonSingular, ComparisonSingularEntity>
        implements ComparisonSingularRepository {
    private final RootTreeDynamicRepository rootTreeDynamicRepository;
    private final RootTreeStaticRepository rootTreeStaticRepository;
    private final RootTreeRepository rootTreeRepository;
    private final NodeTreeBlockRepository nodeTreeBlockRepository;

    ComparisonSingularRepositoryImpl(
            ComparisonSingularMapper mapper,
            EntityManager entityManager,
            RootTreeDynamicRepository rootTreeDynamicRepository,
            RootTreeStaticRepository rootTreeStaticRepository,
            RootTreeRepository rootTreeRepository,
            NodeTreeBlockRepository nodeTreeBlockRepository
    ) {
        super(mapper, new SimpleJpaRepository<>(ComparisonSingularEntity.class, entityManager), ComparisonSingularEntity.class);
        this.rootTreeDynamicRepository = rootTreeDynamicRepository;
        this.rootTreeStaticRepository = rootTreeStaticRepository;
        this.rootTreeRepository = rootTreeRepository;
        this.nodeTreeBlockRepository = nodeTreeBlockRepository;
    }

    @Transactional
    @Override
    public ComparisonSingular createSingComparison(ComparisonSingular newNode, Node<?> parent, RepositoryAuth auth) throws RepositoryException {
        try {
            switch (parent.getNodeType()) {
                case Node.NodeType.ROOT_STATIC:
                    RootTreeStatic parent_static = this.rootTreeStaticRepository.read(parent.getId(), auth);
                    ComparisonSingular createdNodeEntity_0 = this.createSync(newNode, auth);
                    parent_static.addNode(createdNodeEntity_0);
                    this.rootTreeRepository.updateGenericRootSync(parent_static, auth);
                    return this.read(createdNodeEntity_0.getId(), auth);

                case Node.NodeType.ROOT_DYNAMIC:
                    RootTreeDynamic parent_dynamic = this.rootTreeDynamicRepository.read(parent.getId(), auth);
                    ComparisonSingular createdNodeEntity_1 = this.createSync(newNode, auth);
                    parent_dynamic.addNode(createdNodeEntity_1);
                    this.rootTreeRepository.updateGenericRootSync(parent_dynamic, auth);
                    return this.read(createdNodeEntity_1.getId(), auth);

                case Node.NodeType.NODE_BLOCK:
                    NodeTreeBlock parent_node = this.nodeTreeBlockRepository.read(parent.getId(), auth);
                    ComparisonSingular createdNodeEntity_2 = this.createSync(newNode, auth);
                    parent_node.addNode(createdNodeEntity_2);
                    this.nodeTreeBlockRepository.updateSync(parent_node, auth);
                    return this.read(createdNodeEntity_2.getId(), auth);

                default:
                    throw new RepositoryException("Tipo de branch desconhecido: ");
            }
        } catch (Exception e) {
            throw new RepositoryException("Ocorreu um erro ao criar um novo nó.", e);
        }
    }
}

