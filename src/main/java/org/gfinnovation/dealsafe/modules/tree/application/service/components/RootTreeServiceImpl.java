package org.gfinnovation.dealsafe.modules.tree.application.service.components;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe._shared.modules.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.modules.input.domain.valueobjects.predefined.enums.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.application.service.components.interfaces.RootTreeService;
import org.gfinnovation.dealsafe.modules.tree.domain.RootTreeEntity;
import org.gfinnovation.dealsafe.modules.tree.domain.factory.interfaces.TreeFactory;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.TreeRepository;
import org.gfinnovation.dealsafe.modules.tree.domain.valueobjects.RootTreeDynamicEntity;
import org.gfinnovation.dealsafe.modules.tree.domain.valueobjects.RootTreeStaticEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

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
final class RootTreeServiceImpl extends GenericServiceImpl implements RootTreeService {
    private final TreeFactory treeFactory;
    private final TreeRepository treeRepository;

    @Autowired
    public RootTreeServiceImpl(UserBusiness userBusiness, CompanyBusiness companyBusiness, TreeFactory treeFactory, TreeRepository treeRepository) {
        super(userBusiness, companyBusiness);
        this.treeFactory = treeFactory;
        this.treeRepository = treeRepository;
    }

    /**
     * Creates a Root node that references a static input by its type.
     *
     * @param name         Name of given root node.
     * @param static_input Identification of referenced predefined input.
     * @return RootTreeStaticEntity
     * @throws BusinessException Thrown when an error occurs on business level.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */

    @Override
    public CompletableFuture<RootTreeStaticEntity> create(String name, PredefinedTypeEnum static_input) throws BusinessException {
        try {
            return this.treeRepository.getRootTreeStaticRepository()
                    .createAsync(
                            treeFactory.getRootTreeFactory()
                                    .produce(
                                            name,
                                            static_input
                                    ), getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong creating a static root.", e);
        }
    }

    /**
     * Creates a Root node that references a dynamic input by its id.
     *
     * @param name          Name of given root node.
     * @param dynamic_input Identification of referenced dynamic input.
     * @return RootTreeDynamicEntity
     * @throws BusinessException   Thrown when an error occurs on business level.
     * @throws FactoryException    Thrown when an error occurs on factory level.
     * @throws ValidationException Thrown when an error occurs on factory level.
     * @throws RepositoryException Thrown when an error occurs on repository level.
     */
    @Override
    public CompletableFuture<RootTreeDynamicEntity> create(String name, UUID dynamic_input) throws BusinessException {
        try {
            return this.treeRepository.getRootTreeDynamicRepository()
                    .createAsync(treeFactory.getRootTreeFactory()
                            .produce(
                                    name,
                                    dynamic_input
                            ), getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong creating a dynamic root.", e);
        }
    }

    /**
     * Return an object of RootTree, it can be either a RootTreeStatic or RootTreeDynamic,
     * response of this method needs handling.
     *
     * @param id RootId of given root.
     * @return Object(RootTreeEntity)
     * @throws RepositoryException Thrown when an error occur on Repository level.
     * @author Lucas Batista Pereira
     * @since 08/11/2024
     */
    @Override
    public Object readGenericRoot(UUID id) throws BusinessException {
        try {
            return this.treeRepository.readGenericRoot(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: something went wrong reading a generic root.", e);
        }
    }

    /**
     * Returns a root node through a node id.
     *
     * @param node_id NodeId of given node.
     * @return Optional<UUID>
     * @author Lucas Batista Pereira
     * @since 08/11/2024
     */
    @Override
    public Optional<UUID> findRootIdByNodeId(UUID node_id) throws BusinessException {
        try {
            return this.treeRepository.findRootIdByNodeId(node_id);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong find a root by node id.", e);
        }
    }

    /**
     * Update a root, can be either RootTreeStatic or RootTreeDynamic.
     *
     * @param entity Entity to be updated.
     * @return RootTreeEntity
     * @throws RepositoryException Thrown when an error occur on Repository level.
     * @author Lucas Batista Pereira
     * @since 08/11/2024
     */
    @Override
    public RootTreeEntity updateSync(RootTreeEntity entity) throws BusinessException {
        try {
            return this.treeRepository.updateGenericRootSync(entity, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong updating a root.", e);
        }
    }


    @Override
    public Optional<RootTreeStaticEntity> readRootStatic(UUID id) throws BusinessException {
        try {
            return Optional.ofNullable(this.treeRepository.getRootTreeStaticRepository().read(id, getRepositoryAuth()));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading a static root.", e);
        }
    }

    @Override
    public Optional<RootTreeDynamicEntity> readRootDynamic(UUID id) throws BusinessException {
        try {
            return Optional.ofNullable(this.treeRepository.getRootTreeDynamicRepository().read(id, getRepositoryAuth()));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading a dynamic root.", e);
        }
    }

    @Override
    public RootTreeEntity read(UUID id) throws BusinessException {
        try {
            return this.treeRepository.getRootTreeRepository().read(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading a root.", e);
        }
    }

    @Override
    public CompletableFuture<RootTreeEntity> updateAsync(RootTreeEntity entity) throws BusinessException {
        try {
            return this.treeRepository.getRootTreeRepository().updateAsync(entity, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong async updating a root.", e);
        }
    }

    @Override
    public void deleteSync(UUID id) throws BusinessException {
        try {
            this.treeRepository.getRootTreeRepository().deleteSync(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong deleting a root.", e);
        }
    }

    @Override
    public void deleteAsync(UUID id) throws BusinessException {
        try {
            this.treeRepository.getRootTreeRepository().deleteAsync(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong async deleting a root.", e);
        }
    }

    @Override
    public Optional<List<RootTreeEntity>> readAll() throws BusinessException {
        try {
            return this.treeRepository.getRootTreeRepository().findAll(getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading all roots.", e);
        }
    }

    @Override
    public Optional<List<RootTreeEntity>> readAllByIds(List<UUID> ids) throws BusinessException {
        try {
            return this.treeRepository.getRootTreeRepository().findAllByIds(ids, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading all roots by ids.", e);
        }
    }

    @Override
    public void check(UUID id) throws BusinessException {
        try {
            this.treeRepository.getRootTreeRepository().check(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong checking a root.", e);
        }
    }

    @Override
    public void checkAll(Set<UUID> ids) throws BusinessException {
        try {
            this.treeRepository.getRootTreeRepository().checkAll(ids, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong checking all roots by ids.", e);
        }
    }
}
