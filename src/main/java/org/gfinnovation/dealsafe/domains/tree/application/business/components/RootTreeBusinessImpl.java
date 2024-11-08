package org.gfinnovation.dealsafe.domains.tree.application.business.components;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe._shared.infrastructure.GenericBusinessImpl;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
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
class RootTreeBusinessImpl extends GenericBusinessImpl implements RootTreeBusiness {
    private final TreeFactory treeFactory;
    private final TreeRepository treeRepository;

    @Autowired
    public RootTreeBusinessImpl(UserBusiness userBusiness, CompanyBusiness companyBusiness, TreeFactory treeFactory, TreeRepository treeRepository) {
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
     * @throws BusinessException   Thrown when an error occurs on business level.
     * @throws FactoryException    Thrown when an error occurs on factory level.
     * @throws ValidationException Thrown when an error occurs on factory level.
     * @throws RepositoryException Thrown when an error occurs on repository level.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */

    @Override
    public CompletableFuture<RootTreeStaticEntity> create(String name, PredefinedTypeEnum static_input) throws BusinessException, FactoryException, ValidationException, RepositoryException {
        try {
            return this.treeRepository.getRootTreeStaticRepository()
                    .createAsync(
                            getUserId(),
                            getCompanyId(),
                            treeFactory.getRootTreeFactory()
                                    .produce(
                                            getUserId(),
                                            getCompanyId(),
                                            name,
                                            static_input
                                    ));
        } catch (Exception e) {
            throw new BusinessException("Something went wrong when creating a root of type static", e);
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
    public CompletableFuture<RootTreeDynamicEntity> create(String name, UUID dynamic_input) throws BusinessException, FactoryException, ValidationException, RepositoryException {
        try {
            return this.treeRepository.getRootTreeDynamicRepository()
                    .createAsync(
                            getUserId(),
                            getCompanyId(),
                            treeFactory.getRootTreeFactory()
                                    .produce(
                                            getUserId(),
                                            getCompanyId(),
                                            name,
                                            dynamic_input
                                    ));
        } catch (FactoryException | RepositoryException | ValidationException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Something went wrong when creating a root of type dynamic", e);
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
    public Object readGenericRoot(UUID id) throws RepositoryException, BusinessException {
        try {
            return this.treeRepository.readGenericRoot(getUserId(), getCompanyId(), id);
        } catch (RepositoryException e){
            throw e;
        } catch (Exception e){
            throw new BusinessException("something went wrong reading a root.", e);
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
    public Optional<UUID> findRootIdByNodeId(UUID node_id) {
        return this.treeRepository.findRootIdByNodeId(node_id);
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
    public RootTreeEntity updateSync(RootTreeEntity entity) throws RepositoryException {
        return this.treeRepository.updateGenericRootSync(getUserId(), getCompanyId(), entity);
    }


    @Override
    public Optional<RootTreeStaticEntity> readRootStatic(UUID id) throws RepositoryException {
        return Optional.ofNullable(this.treeRepository.getRootTreeStaticRepository().read(getUserId(), getCompanyId(), id));
    }

    @Override
    public Optional<RootTreeDynamicEntity> readRootDynamic(UUID id) throws RepositoryException {
        return Optional.ofNullable(this.treeRepository.getRootTreeDynamicRepository().read(getUserId(), getCompanyId(), id));
    }

    @Override
    public RootTreeEntity read(UUID id) throws RuntimeException {
        return this.treeRepository.getRootTreeRepository().read(getUserId(), getCompanyId(), id);
    }

    @Override
    public CompletableFuture<RootTreeEntity> updateAsync(RootTreeEntity entity) throws RuntimeException {
        return this.treeRepository.getRootTreeRepository().updateAsync(getUserId(), getCompanyId(), entity);
    }

    @Override
    public void deleteSync(UUID id) throws RuntimeException {
        this.treeRepository.getRootTreeRepository().deleteSync(getUserId(), getCompanyId(), id);
    }

    @Override
    public void deleteAsync(UUID id) throws RuntimeException {
        this.treeRepository.getRootTreeRepository().deleteAsync(getUserId(), getCompanyId(), id);
    }

    @Override
    public Optional<List<RootTreeEntity>> readAll() throws RuntimeException {
        return this.treeRepository.getRootTreeRepository().findAll(getUserId(), getCompanyId());
    }

    @Override
    public Optional<List<RootTreeEntity>> readAllByIds(List<UUID> ids) throws RuntimeException {
        return this.treeRepository.getRootTreeRepository().findAllByIds(getUserId(), getCompanyId(), ids);
    }

    @Override
    public void check(UUID id) throws RuntimeException {
        this.treeRepository.getRootTreeRepository().check(getUserId(), getCompanyId(), id);
    }

    @Override
    public void checkAll(Set<UUID> ids) throws RuntimeException {
        this.treeRepository.getRootTreeRepository().checkAll(getUserId(), getCompanyId(), ids);
    }
}
