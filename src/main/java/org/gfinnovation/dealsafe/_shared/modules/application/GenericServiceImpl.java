package org.gfinnovation.dealsafe._shared.modules.application;

import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe._shared.modules.domain.GenericBusinessClass;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.interfaces.GenericBusinessRepository;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Every single factory that is not linked to authentication entities should extend
 * from this.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class GenericBusinessFactory
 * @since 30/10/2024
 */


//TODO: Segregar commands de queries. Definir commands como protected e Query como public.
public abstract class GenericServiceImpl
        <E extends GenericBusinessClass, R extends GenericBusinessRepository<E>>
        extends GenericAuthDomainServiceImpl
        implements GenericService<E> {
    protected final R repository;

    protected GenericServiceImpl(R repository) {
        this.repository = repository;
    }

    public E read(UUID id) throws ServiceException {
        try {
            return this.repository.read(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading an entity.", e);
        }
    }

    public CompletableFuture<E> updateAsync(E entity) throws ServiceException {
        try {
            return this.repository.updateAsync(entity, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong async updating an entity.", e);
        }
    }

    public E updateSync(E entity) throws ServiceException {
        try {
            return this.repository.updateSync(entity, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong updating an entity.", e);
        }
    }

    public void deleteAsync(UUID id) throws ServiceException {
        try {
            this.repository.deleteAsync(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong async deleting an entity.", e);
        }
    }

    public void deleteSync(UUID id) throws ServiceException {
        try {
            this.repository.deleteSync(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong deleting an entity.", e);
        }
    }

    public Optional<List<E>> readAll() throws ServiceException {
        try {
            return this.repository.findAll(getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading all entities.", e);
        }
    }

    public Optional<List<E>> readAllByIds(List<UUID> ids) throws ServiceException {
        try {
            return this.repository.findAllByIds(ids, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading all entities by id.", e);
        }
    }

    public void check(UUID id) throws ServiceException {
        try {
            this.repository.check(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong checking an entity.", e);
        }
    }

    public void checkAll(Set<UUID> ids) throws ServiceException {
        try {
            this.repository.checkAll(ids, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong checking all entities by ids.", e);
        }
    }
}