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
import org.gfinnovation.dealsafe.modules.tree.domain.TreeRoot;
import org.gfinnovation.dealsafe.modules.tree.domain.factory.interfaces.TreeFactory;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.TreeRepository;
import org.gfinnovation.dealsafe.modules.tree.domain.valueobjects.TreeDynamicRoot;
import org.gfinnovation.dealsafe.modules.tree.domain.valueobjects.TreeStaticRoot;
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
    public RootTreeServiceImpl(
            UserBusiness userBusiness,
            CompanyBusiness companyBusiness,
            TreeFactory treeFactory,
            TreeRepository treeRepository)
    {
        super(userBusiness, companyBusiness);
        this.treeFactory = treeFactory;
        this.treeRepository = treeRepository;
    }

    /**
     * Creates a Root node that references a static input by its type.
     *
     * @param name         Name of given root node.
     * @param static_input Identification of referenced predefined input.
     * @return TreeStaticRoot
     * @throws BusinessException Thrown when an error occurs on business level.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */

    @Override
    public CompletableFuture<TreeStaticRoot> create(String name, PredefinedTypeEnum static_input) throws BusinessException {
        try {
            return this.treeRepository.getTreeStaticRootRepository()
                    .createAsync(
                            treeFactory.getTreeRootFactory()
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
     * @return TreeDynamicRoot
     * @throws BusinessException   Thrown when an error occurs on business level.
     * @throws FactoryException    Thrown when an error occurs on factory level.
     * @throws ValidationException Thrown when an error occurs on factory level.
     * @throws RepositoryException Thrown when an error occurs on repository level.
     */
    @Override
    public CompletableFuture<TreeDynamicRoot> create(String name, UUID dynamic_input) throws BusinessException {
        try {
            return this.treeRepository.getTreeDynamicRootRepository()
                    .createAsync(treeFactory.getTreeRootFactory()
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
     * @return Object(TreeRoot)
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
     * @return TreeRoot
     * @throws RepositoryException Thrown when an error occur on Repository level.
     * @author Lucas Batista Pereira
     * @since 08/11/2024
     */
    @Override
    public TreeRoot updateSync(TreeRoot entity) throws BusinessException {
        try {
            return this.treeRepository.updateGenericRootSync(entity, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong updating a root.", e);
        }
    }


    @Override
    public Optional<TreeStaticRoot> readRootStatic(UUID id) throws BusinessException {
        try {
            return Optional.ofNullable(this.treeRepository.getTreeStaticRootRepository().read(id, getRepositoryAuth()));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading a static root.", e);
        }
    }

    @Override
    public Optional<TreeDynamicRoot> readRootDynamic(UUID id) throws BusinessException {
        try {
            return Optional.ofNullable(this.treeRepository.getTreeDynamicRootRepository().read(id, getRepositoryAuth()));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading a dynamic root.", e);
        }
    }

    @Override
    public TreeRoot read(UUID id) throws BusinessException {
        try {
            return this.treeRepository.getTreeRootRepository().read(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading a root.", e);
        }
    }

    @Override
    public CompletableFuture<TreeRoot> updateAsync(TreeRoot entity) throws BusinessException {
        try {
            return this.treeRepository.getTreeRootRepository().updateAsync(entity, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong async updating a root.", e);
        }
    }

    @Override
    public void deleteSync(UUID id) throws BusinessException {
        try {
            this.treeRepository.getTreeRootRepository().deleteSync(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong deleting a root.", e);
        }
    }

    @Override
    public void deleteAsync(UUID id) throws BusinessException {
        try {
            this.treeRepository.getTreeRootRepository().deleteAsync(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong async deleting a root.", e);
        }
    }

    @Override
    public Optional<List<TreeRoot>> readAll() throws BusinessException {
        try {
            return this.treeRepository.getTreeRootRepository().findAll(getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading all roots.", e);
        }
    }

    @Override
    public Optional<List<TreeRoot>> readAllByIds(List<UUID> ids) throws BusinessException {
        try {
            return this.treeRepository.getTreeRootRepository().findAllByIds(ids, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading all roots by ids.", e);
        }
    }

    @Override
    public void check(UUID id) throws BusinessException {
        try {
            this.treeRepository.getTreeRootRepository().check(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong checking a root.", e);
        }
    }

    @Override
    public void checkAll(Set<UUID> ids) throws BusinessException {
        try {
            this.treeRepository.getTreeRootRepository().checkAll(ids, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong checking all roots by ids.", e);
        }
    }
}
