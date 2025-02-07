package org.gfinnovation.dealsafe._shared.modules.application;

import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe._shared.modules.domain.GenericBusinessClass;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.interfaces.GenericBusinessRepository;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class GenericWriteOperationsServiceImpl
 * @since v1.0 (07/02/2025)
 */
abstract class GenericWriteOperationsServiceImpl
        <E extends GenericBusinessClass, R extends GenericBusinessRepository<E>>
        extends GenericAuthDomainServiceImpl
        implements GenericService<E> {
    protected final R repository;

    protected GenericWriteOperationsServiceImpl(R repository) {
        this.repository = repository;
    }

    protected E create(E entity) throws SystemGlobalException {
        try {
            return this.repository.create(entity, getRepositoryAuth());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong creating an entity.");
        }
    }

    protected E update(E entity) throws SystemGlobalException {
        try {
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

    protected CompletableFuture<E> updateAsync(E entity) throws SystemGlobalException {
        try {
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
