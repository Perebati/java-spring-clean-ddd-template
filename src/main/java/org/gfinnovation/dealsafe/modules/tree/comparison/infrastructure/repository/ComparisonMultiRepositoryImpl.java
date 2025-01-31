package org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.gfinnovation.dealsafe._shared.modules.application.RepositoryAuth;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.repository.interfaces.ComparisonMultiRepository;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.ComparisonMultiEntity;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.mapper.ComparisonMultiMapper;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.node.repository.interfaces.NodeTreeBlockRepository;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.RootTreeStatic;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.root.repository.interfaces.RootTreeDynamicRepository;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.root.repository.interfaces.RootTreeRepository;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.root.repository.interfaces.RootTreeStaticRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ComparisonMultiRepositoryImpl
        extends GenericBusinessRepositoryImpl<ComparisonMulti, ComparisonMultiEntity>
        implements ComparisonMultiRepository {
    private final RootTreeDynamicRepository rootTreeDynamicRepository;
    private final RootTreeStaticRepository rootTreeStaticRepository;
    private final RootTreeRepository rootTreeRepository;
    private final NodeTreeBlockRepository nodeTreeBlockRepository;

    ComparisonMultiRepositoryImpl(
            ComparisonMultiMapper mapper,
            EntityManager entityManager,
            RootTreeDynamicRepository rootTreeDynamicRepository,
            RootTreeStaticRepository rootTreeStaticRepository,
            RootTreeRepository rootTreeRepository,
            NodeTreeBlockRepository nodeTreeBlockRepository
    ) {
        super(mapper, new SimpleJpaRepository<>(ComparisonMultiEntity.class, entityManager), ComparisonMultiEntity.class);
        this.rootTreeDynamicRepository = rootTreeDynamicRepository;
        this.rootTreeStaticRepository = rootTreeStaticRepository;
        this.rootTreeRepository = rootTreeRepository;
        this.nodeTreeBlockRepository = nodeTreeBlockRepository;
    }

    @Transactional
    @Override
    public ComparisonMulti createMultiComparison(ComparisonMulti newNode, Node<?> parent, RepositoryAuth auth) throws RepositoryException {
        try {
            switch (parent.getNodeType()) {
                case Node.NodeType.ROOT_STATIC:
                    RootTreeStatic parent_static = this.rootTreeStaticRepository.read(parent.getId(), auth);
                    ComparisonMulti createdNodeEntity_0 = this.createSync(newNode, auth);
                    parent_static.addNode(createdNodeEntity_0);
                    this.rootTreeRepository.updateGenericRootSync(parent_static, auth);
                    return this.read(createdNodeEntity_0.getId(), auth);

                case Node.NodeType.ROOT_DYNAMIC:
                    RootTreeDynamic parent_dynamic = this.rootTreeDynamicRepository.read(parent.getId(), auth);
                    ComparisonMulti createdNodeEntity_1 = this.createSync(newNode, auth);
                    parent_dynamic.addNode(createdNodeEntity_1);
                    this.rootTreeRepository.updateGenericRootSync(parent_dynamic, auth);
                    return this.read(createdNodeEntity_1.getId(), auth);

                case Node.NodeType.NODE_BLOCK:
                    NodeTreeBlock parent_node = this.nodeTreeBlockRepository.read(parent.getId(), auth);
                    ComparisonMulti createdNodeEntity_2 = this.createSync(newNode, auth);
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