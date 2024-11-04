package org.gfinnovation.dealsafe.domains.tree.application.business.components;

import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
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
 * Handles business operations of RootNode.
 * A root is strongly linked to the input type, that can be
 * either a predefined input or a dynamic one. Depending on the chosen input
 * type, it will be crated a RootStatic (for predefined input) or a RootDynamic (for dynamic input).
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

    /**
     * Creates a Root node that references a static input by its type.
     *
     * @param user_id      UserId.
     * @param company_id   CompanyId.
     * @param name         Name of given root node.
     * @param static_input Identification of referenced predefined input.
     * @return RootTreeStaticEntity
     * @throws BusinessException   Thrown when an error occurs on business level.
     * @throws FactoryException    Thrown when an error occurs on factory level.
     * @throws ValidationException Thrown when an error occurs on factory level.
     * @throws RepositoryException Thrown when an error occurs on repository level.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */

    @Override
    @Transactional
    public RootTreeStaticEntity create(UUID user_id, UUID company_id, String name, PredefinedTypeEnum static_input) throws BusinessException, FactoryException, ValidationException, RepositoryException {
        try {
            return this.treeRepository.getRootTreeStaticRepository().create(treeFactory.getRootTreeFactory().produce(user_id, company_id, name, static_input));
        } catch (Exception e) {
            throw new BusinessException("Something went wrong when creating a root of type static", e);
        }
    }

    /**
     * Creates a Root node that references a dynamic input by its id.
     *
     * @param user_id       UserId.
     * @param company_id    CompanyId.
     * @param name          Name of given root node.
     * @param dynamic_input Identification of referenced dynamic input.
     * @return RootTreeDynamicEntity
     * @throws BusinessException   Thrown when an error occurs on business level.
     * @throws FactoryException    Thrown when an error occurs on factory level.
     * @throws ValidationException Thrown when an error occurs on factory level.
     * @throws RepositoryException Thrown when an error occurs on repository level.
     */
    @Override
    @Transactional
    public RootTreeDynamicEntity create(UUID user_id, UUID company_id, String name, UUID dynamic_input) throws BusinessException, FactoryException, ValidationException, RepositoryException {
        try {
            return this.treeRepository.getRootTreeDynamicRepository().create(treeFactory.getRootTreeFactory().produce(user_id, company_id, name, dynamic_input));
        } catch (Exception e) {
            throw new BusinessException("Something went wrong when creating a root of type dynamic", e);
        }
    }

    /**
     * Gets a root by its id. The response needs handling.
     *
     * @param id Given rootId.
     * @return Object
     * @throws RepositoryException Thrown when an error occurs on repository level.
     */
    @Override
    public Object readGenericRoot(UUID id) throws RepositoryException {
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
    public Optional<RootTreeStaticEntity> readRootStatic(UUID id) throws RepositoryException {
        return this.treeRepository.getRootTreeStaticRepository().read(id);
    }

    @Override
    public Optional<RootTreeDynamicEntity> readRootDynamic(UUID id) throws RepositoryException {
        return this.treeRepository.getRootTreeDynamicRepository().read(id);
    }

    @Override
    public Optional<UUID> findRootIdByNodeId(UUID node_id) {
        return this.treeRepository.findRootIdByNodeId(node_id);
    }

    @Override
    public Optional<RootTreeEntity> read(UUID id) throws RepositoryException {
        return this.treeRepository.getRootTreeRepository().read(id);
    }

    @Override
    public RootTreeEntity update(RootTreeEntity entity) throws RepositoryException {
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
    public void delete(UUID id) throws RepositoryException {
        this.treeRepository.getRootTreeRepository().delete(id);
    }

    @Override
    public Optional<List<RootTreeEntity>> readAll() throws RepositoryException {
        return this.treeRepository.getRootTreeRepository().findAll();
    }

    @Override
    public Optional<List<RootTreeEntity>> readAllByIds(List<UUID> ids) throws RepositoryException {
        return this.treeRepository.getRootTreeRepository().findAllByIds(ids);
    }

    @Override
    public void check(UUID id) throws RepositoryException {
        this.treeRepository.getRootTreeRepository().check(id);
    }

    @Override
    public void checkAll(Set<UUID> ids) throws RepositoryException {
        this.treeRepository.getRootTreeRepository().checkAll(ids);
    }
}
