package org.gfinnovation.dealsafe._shared.application;

import org.gfinnovation.dealsafe._shared.application.interfaces.GenericService;
import org.gfinnovation.dealsafe._shared.domain.GenericBusinessClass;
import org.gfinnovation.dealsafe._shared.infrastructure.repository.interfaces.GenericBusinessRepository;
import org.gfinnovation.dealsafe.utils.converter.ObjectMerger;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * This class has most of CRUD operations built in. It also handles
 * Repository communication and authorization.
 * It also handles possibles errors.
 * Every new service class created in this system should be extended from this,
 * as it makes things easier for developers.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class GenericServiceImpl
 * @since v1.0 (24/01/2025)
 */
public abstract class GenericServiceImpl
        <E extends GenericBusinessClass, R extends GenericBusinessRepository<E>>
        extends GenericAuthDomainServiceImpl
        implements GenericService<E> {
    protected final R repository;

    protected GenericServiceImpl(R repository1) {
        this.repository = repository1;
    }

    /*

    ------------------- Public Read Operations -------------------

    */

    public E read(UUID id) throws SystemGlobalException {
        try {
            return this.repository.read(id, getRepositoryAuth());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong reading an entity.");
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

    /*

        ------------------- Protected Write Operations -------------------

     */

    protected E create(E entity) throws SystemGlobalException {
        try {
            return this.repository.create(entity, getRepositoryAuth());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong creating an entity.");
        }
    }

    protected <D> E update(UUID id, D data) throws SystemGlobalException {
        try {
            E entity = this.read(id);
            ObjectMerger.mergeObjects(entity, data);
            return this.repository.update(entity, getRepositoryAuth());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong updating an entity.");
        }
    }

    protected void delete(UUID id) throws SystemGlobalException {
        try {
            this.repository.delete(id, getRepositoryAuth());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong deleting an entity.");
        }
    }

    protected <D> CompletableFuture<E> updateAsync(UUID id, D data) throws SystemGlobalException {
        try {
            E entity = this.read(id);
            ObjectMerger.mergeObjects(entity, data);
            return this.repository.updateAsync(entity, getRepositoryAuth());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong async updating an entity.");
        }
    }

    protected void deleteAsync(UUID id) throws SystemGlobalException {
        try {
            this.repository.deleteAsync(id, getRepositoryAuth());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong async deleting an entity.");
        }
    }
}