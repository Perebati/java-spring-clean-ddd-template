package org.gfinnovation.dealsafe.modules.tree.root.application.service;

import org.gfinnovation.dealsafe._shared.modules.application.GenericAuthDomainServiceImpl;
import org.gfinnovation.dealsafe.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTree;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository.interfaces.RootTreeRepository;
import org.gfinnovation.dealsafe.modules.tree.root.application.service.interfaces.RootTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
class RootTreeServiceImpl
        extends GenericAuthDomainServiceImpl
        implements RootTreeService {
    private final RootTreeRepository rootTreeRepository;

    @Autowired
    public RootTreeServiceImpl(
            RootTreeRepository rootTreeRepository
    ) {
        this.rootTreeRepository = rootTreeRepository;
    }

    /**
     * Return an object of RootTree, it can be either a RootTreeStatic or RootTreeDynamic,
     * response of this method needs handling.
     *
     * @param id RootId of given root.
     * @return Object(RootTree)
     * @throws RepositoryException Thrown when an error occur on Repository level.
     * @author Lucas Batista Pereira
     * @since 08/11/2024
     */
    @Override
    public Object readGenericRoot(UUID id) throws ServiceException {
        try {
            return this.rootTreeRepository.readGenericRoot(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: something went wrong reading a generic root.", e);
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
    public Optional<UUID> findRootIdByNodeId(UUID node_id) throws ServiceException {
        try {
            return this.rootTreeRepository.findRootIdByNodeId(node_id);
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong find a root by node id.", e);
        }
    }

    /**
     * Update a root, can be either RootTreeStatic or RootTreeDynamic.
     *
     * @param entity Entity to be updated.
     * @return RootTree
     * @throws RepositoryException Thrown when an error occur on Repository level.
     * @author Lucas Batista Pereira
     * @since 08/11/2024
     */
    @Override
    public RootTree<?> updateSync(RootTree<?> entity) throws ServiceException {
        try {
            return this.rootTreeRepository.updateGenericRootSync(entity, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong updating a root.", e);
        }
    }

    @Override
    public RootTree<?> read(UUID id) throws ServiceException {
        try {
            return this.rootTreeRepository.read(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading a root.", e);
        }
    }

    @Override
    public CompletableFuture<RootTree<?>> updateAsync(RootTree<?> entity) throws ServiceException {
        try {
            return this.rootTreeRepository.updateAsync(entity, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong async updating a root.", e);
        }
    }

    @Override
    public void deleteSync(UUID id) throws ServiceException {
        try {
            this.rootTreeRepository.deleteSync(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong deleting a root.", e);
        }
    }

    @Override
    public void deleteAsync(UUID id) throws ServiceException {
        try {
            this.rootTreeRepository.deleteAsync(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong async deleting a root.", e);
        }
    }

    @Override
    public Optional<List<RootTree<?>>> readAll() throws ServiceException {
        try {
            return this.rootTreeRepository.findAll(getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading all roots.", e);
        }
    }

    @Override
    public Optional<List<RootTree<?>>> readAllByIds(List<UUID> ids) throws ServiceException {
        try {
            return this.rootTreeRepository.findAllByIds(ids, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading all roots by ids.", e);
        }
    }

    @Override
    public void check(UUID id) throws ServiceException {
        try {
            this.rootTreeRepository.check(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong checking a root.", e);
        }
    }

    @Override
    public void checkAll(Set<UUID> ids) throws ServiceException {
        try {
            this.rootTreeRepository.checkAll(ids, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong checking all roots by ids.", e);
        }
    }
}