package org.gfinnovation.dealsafe._shared.modules.application;

import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe._shared.modules.domain.GenericBusinessClass;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.interfaces.GenericBusinessRepository;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;

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

//TODO: Comentar o código
//TODO: Segregar commands de queries. Definir commands como protected e Query como public.
public abstract class GenericServiceImpl
        <E extends GenericBusinessClass, R extends GenericBusinessRepository<E>>
        extends GenericAuthDomainServiceImpl
        implements GenericService<E> {
    protected final R repository;

    protected GenericServiceImpl(R repository) {
        this.repository = repository;
    }

    public E read(UUID id) throws SystemGlobalException {
        try {
            return this.repository.read(id, getRepositoryAuth());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong reading an entity.");
        }
    }

    public CompletableFuture<E> updateAsync(E entity) throws SystemGlobalException {
        try {
            return this.repository.updateAsync(entity, getRepositoryAuth());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong async updating an entity.");
        }
    }

    public E updateSync(E entity) throws SystemGlobalException {
        try {
            return this.repository.update(entity, getRepositoryAuth());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong updating an entity.");
        }
    }

    public void deleteAsync(UUID id) throws SystemGlobalException {
        try {
            this.repository.deleteAsync(id, getRepositoryAuth());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong async deleting an entity.");
        }
    }

    public void deleteSync(UUID id) throws SystemGlobalException {
        try {
            this.repository.delete(id, getRepositoryAuth());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong deleting an entity.");
        }
    }

    public Optional<List<E>> readAll() throws SystemGlobalException {
        try {
            return this.repository.findAll(getRepositoryAuth());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong reading all entities.");
        }
    }

    public Optional<List<E>> readAllByIds(List<UUID> ids) throws SystemGlobalException {
        try {
            return this.repository.findAllByIds(ids, getRepositoryAuth());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong reading all entities by id.");
        }
    }

    public void check(UUID id) throws SystemGlobalException {
        try {
            this.repository.check(id, getRepositoryAuth());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong checking an entity.");
        }
    }

    public void checkAll(Set<UUID> ids) throws SystemGlobalException {
        try {
            this.repository.checkAll(ids, getRepositoryAuth());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong checking all entities by ids.");
        }
    }
}