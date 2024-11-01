package org.gfinnovation.dealsafe.domains.tree.application.business.components;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.gfinnovation.dealsafe.domains.input.entity.predefined.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.domains.tree.application.business.components.interfaces.RootTreeBusiness;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeDynamicEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeStaticEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.factory.interfaces.TreeFactory;
import org.gfinnovation.dealsafe.domains.tree.entity.repository.TreeRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 *  Handles business operations of RootNode.
 *  A root is strongly linked to the input type, that can be
 *  either a predefined input or a dynamic one. Depending on the chosen input
 *  type, it will be crated a RootStatic (for predefined input) or a RootDynamic (for dynamic input).
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootTreeBusinessImpl
 * @since 30/10/2024
 */
@Component
@RequiredArgsConstructor
class RootTreeBusinessImpl implements RootTreeBusiness {
    private final TreeFactory treeFactory;
    private final TreeRepository treeRepository;

    @Override
    @Transactional
    public RootTreeStaticEntity create(UUID user_id, UUID company_id, String name, PredefinedTypeEnum static_input) throws RuntimeException {
        return this.treeRepository.getRootTreeStaticRepository().create(treeFactory.getRootTreeFactory().produce(user_id, company_id, name, static_input));
    }

    @Override
    @Transactional
    public RootTreeDynamicEntity create(UUID user_id, UUID company_id, String name, UUID dynamic_input) throws RuntimeException {
        return this.treeRepository.getRootTreeDynamicRepository().create(treeFactory.getRootTreeFactory().produce(user_id, company_id, name, dynamic_input));
    }

    @Override
    public Object readGenericRoot(UUID id) {
        Optional<RootTreeDynamicEntity> dynamicRoot = this.treeRepository.getRootTreeDynamicRepository().read(id);
        if (dynamicRoot.isPresent()) {
            return dynamicRoot.get();
        }

        Optional<RootTreeStaticEntity> staticRoot = this.treeRepository.getRootTreeStaticRepository().read(id);
        if (staticRoot.isPresent()) {
            return staticRoot.get();
        }

        return Optional.empty();
    }

    @Override
    public Optional<RootTreeStaticEntity> readRootStatic(UUID id) throws RuntimeException {
        return this.treeRepository.getRootTreeStaticRepository().read(id);
    }

    @Override
    public Optional<RootTreeDynamicEntity> readRootDynamic(UUID id) throws RuntimeException {
        return this.treeRepository.getRootTreeDynamicRepository().read(id);
    }

    @Override
    public Optional<UUID> findRootIdByNodeId(UUID node_id) {
        return this.treeRepository.findRootIdByNodeId(node_id);
    }

    @Override
    public Optional<RootTreeEntity> read(UUID id) throws RuntimeException {
        return this.treeRepository.getRootTreeRepository().read(id);
    }

    @Override
    public RootTreeEntity update(RootTreeEntity entity) throws RuntimeException {
        try {
            if (entity instanceof RootTreeDynamicEntity dynamicRoot) {
                return this.treeRepository.getRootTreeDynamicRepository().update(dynamicRoot);
            } else if (entity instanceof RootTreeStaticEntity staticRoot) {
                return this.treeRepository.getRootTreeStaticRepository().update(staticRoot);
            } else {
                throw new IllegalArgumentException("Unexpected RootTreeEntity type: " + entity.getClass().getName());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to patch RootTreeEntity. ERROR_CODE: PATCH-01", e);
        }
    }

    @Override
    public void delete(UUID id) throws RuntimeException {
        this.treeRepository.getRootTreeRepository().delete(id);
    }

    @Override
    public Optional<List<RootTreeEntity>> readAll() throws RuntimeException {
        return this.treeRepository.getRootTreeRepository().findAll();
    }

    @Override
    public Optional<List<RootTreeEntity>> readAllByIds(List<UUID> ids) throws RuntimeException {
        return this.treeRepository.getRootTreeRepository().findAllByIds(ids);
    }

    @Override
    public void check(UUID id) throws RuntimeException {
        this.treeRepository.getRootTreeRepository().check(id);
    }

    @Override
    public void checkAll(Set<UUID> ids) throws RuntimeException {
        this.treeRepository.getRootTreeRepository().checkAll(ids);
    }
}
