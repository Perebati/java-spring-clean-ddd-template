package org.gfinnovation.dealsafe._shared.modules.infrastructure.repository;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.gfinnovation.dealsafe._shared.modules.domain.GenericBusinessEntity;
import org.gfinnovation.dealsafe._shared.modules.domain.repository.GenericBusinessRepository;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.GenericBusinessSchema;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.mapper.GenericBusinessMapper;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.components.GenericBusinessJpaRepositoryImpl;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryEntityNotFoundException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.configuration.security.RepositoryAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.scheduling.annotation.Async;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/*
 * TODO: Need revision!. UPDATE: ALL DONE!!
 * TODO: Use MDC for getting company_id and user_id instead of passing the args through method header manually. UPDATE: It's not recommended use MDC on asynchronous methods!
 * TODO: Use native JPA structures to keep track of creation, update and deletion dateTimes. UPDATE: DONE!
 * TODO: Try to optimize the way how company_id and user_id are being filtered on read functions. UPDATE: DONE!
 */

/**
 * This is a business version of GenericRepositoryImpl.
 * This repository should be used for any entity/domain that is dependent of business rules.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class GenericBusinessRepositoryImpl
 * @since 01/11/2024
 */

public class GenericBusinessRepositoryImpl<E extends GenericBusinessEntity, S extends GenericBusinessSchema>
        extends GenericBusinessJpaRepositoryImpl<S> implements GenericBusinessRepository<E> {

    private static final Logger logger = LoggerFactory.getLogger(GenericRepositoryImpl.class);
    private final GenericBusinessMapper<E, S> mapper;
    private final SimpleJpaRepository<S, UUID> jpaRepository;
    private final Class<S> entityClass;


    /**
     * Every domain repository should have a super constructor for this class.
     *
     * @param mapper        GenericMapper for any domain class.
     * @param jpaRepository SimpleJpaRepository class for general use.
     * @param entityClass   Reference class for infra operations.
     */

    @Autowired
    public GenericBusinessRepositoryImpl(GenericBusinessMapper<E, S> mapper, SimpleJpaRepository<S, UUID> jpaRepository, Class<S> entityClass) {
        this.mapper = mapper;
        this.jpaRepository = jpaRepository;
        this.entityClass = entityClass;
    }


    /**
     * Some methods of this class are asynchronous, so using MDC here would be
     * a big risk. That's why it's necessary to provide user and company id in every method.
     *
     * @param schema Reference schema.
     * @author Lucas Batista Pereira
     * @since 06/11/2024
     */

    private void setCommonFields(S schema, RepositoryAuth auth) {
        schema.setUser_id(auth.user_id());
        schema.setCompany_id(auth.company_id());
    }

    /*
            --------------     Async Operations     --------------
     */

    /**
     * In the future, creation will be done on a remote database using
     * kafka queues, that's why it needs to be async.
     *
     * @param entity Entity that extends GenericBusinessEntity
     * @return CompletableFuture<E>
     * @throws RepositoryException Thrown when an unexpected database error occurs.
     * @author Lucas Batista Pereira
     * @since 06/11/2024
     */
    @Override
    @Async
    @Transactional
    public CompletableFuture<E> createAsync(@NotNull E entity, @NotNull RepositoryAuth auth) throws RepositoryException {
        try {
            S schema = mapper.toSchema(entity);
            setCommonFields(schema, auth);
            S savedSchema = jpaRepository.save(schema);
            logger.info("A new {} was created in the system!", entity.getClass().getSimpleName());
            return CompletableFuture.completedFuture(mapper.toEntity(savedSchema));
        } catch (Exception e) {
            logger.error("Failed to save entity: {}", entity, e);
            throw new RepositoryException("Repository: Failed to async save entity", e);
        }
    }

    /**
     * Update a schema based on an entity.
     *
     * @param entity Entity to be updated.
     * @return CompletableFuture<E>
     * @throws RepositoryException Thrown when an unexpected database error occurs.
     * @author Lucas Batista Pereira
     * @since 06/11/2024
     */
    @Override
    @Async
    @Transactional
    public CompletableFuture<E> updateAsync(@NotNull E entity, @NotNull RepositoryAuth auth) throws RepositoryException {
        try {
            this.readInternal(entity.getId(), auth);
            S existingSchema = mapper.toSchema(entity);
            S savedSchema = jpaRepository.save(existingSchema);
            logger.info("An existing entity of class {} is being updated in the system!", entity.getClass().getSimpleName());
            return CompletableFuture.completedFuture(mapper.toEntity(savedSchema));
        } catch (Exception e) {
            logger.error("Failed to update entity: {}", entity, e);
            throw new RepositoryException("Repository: Failed to async update entity", e);
        }
    }

    /**
     * Soft delete an entity.
     *
     * @param id Reference for entity to be deleted.
     * @throws RepositoryException Thrown when an unexpected database error occurs.
     * @author Lucas Batista Pereira
     * @since 06/11/2024
     */
    @Override
    @Async
    @Transactional
    public void deleteAsync(@NotNull UUID id, @NotNull RepositoryAuth auth) throws RepositoryException {
        try {
            delete(id, auth);
        } catch (Exception e) {
            logger.error("Failed to delete entity with id: {}", id, e);
            throw new RepositoryException("Repository: Failed to async delete entity", e);
        }
    }

    /*
            --------------     Sync Operations     --------------
     */

    /**
     * Sync version of create.
     *
     * @param entity Entity that extends GenericBusinessEntity
     * @return CompletableFuture<E>
     * @throws RepositoryException Thrown when an unexpected database error occurs.
     * @author Lucas Batista Pereira
     * @since 06/11/2024
     */
    @Override
    @Transactional
    public E createSync(@NotNull E entity, @NotNull RepositoryAuth auth) throws RepositoryException {
        try {
            S schema = mapper.toSchema(entity);
            setCommonFields(schema, auth);
            S savedSchema = jpaRepository.save(schema);
            logger.info("A new {} was created in the system!", entity.getClass().getSimpleName());
            return mapper.toEntity(savedSchema);
        } catch (Exception e) {
            logger.error("Failed to save entity: {}", entity, e);
            throw new RepositoryException("Repository: Failed to save entity", e);
        }
    }

    /**
     * Standard reading method.
     *
     * @param id Reference entity id.
     * @return Entity
     * @throws RepositoryException               Thrown when an unexpected database error occurs.
     * @throws RepositoryEntityNotFoundException Thrown when an entity is not found!
     * @author Lucas Batista Pereira
     * @since 06/11/2024
     */
    @Override
    @Transactional
    public E read(@NotNull UUID id, @NotNull RepositoryAuth auth) throws RepositoryException {
        try {
            S result = this.findById(id, auth.company_id(), entityClass).get();
            return mapper.toEntity(result);
        } catch (Exception e) {
            logger.error("Failed to retrieve entity with id: {}", id, e);
            throw new RepositoryException("Repository: Failed to retrieve data", e);
        }
    }


    /**
     * Protected class, for internal use only.
     *
     * @param id Reference entity id.
     * @return Schema
     * @throws RepositoryException               Thrown when an unexpected database error occurs.
     * @throws RepositoryEntityNotFoundException Thrown when an entity is not found!
     * @author Lucas Batista Pereira
     * @since 06/11/2024
     */
    protected S readInternal(@NotNull UUID id, @NotNull RepositoryAuth auth) throws RepositoryException {
        try {
            return this.findById(id, auth.company_id(), entityClass)
                    .orElseThrow(() -> new RepositoryEntityNotFoundException("Repository: Entity not found or already deleted with id: " + id));
        } catch (Exception e) {
            logger.error("Failed to retrieve entity with id: {}", id, e);
            throw new RepositoryException("Repository: Failed to retrieve data", e);
        }
    }

    /**
     * Sync version of Update method.
     *
     * @param entity Entity to be updated.
     * @return CompletableFuture<E>
     * @throws RepositoryException Thrown when an unexpected database error occurs.
     * @author Lucas Batista Pereira
     * @since 06/11/2024
     */
    @Override
    @Transactional
    public E updateSync(@NotNull E entity, @NotNull RepositoryAuth auth) throws RepositoryException {
        try {
            this.readInternal(entity.getId(), auth);
            S existingSchema = mapper.toSchema(entity);
            S savedSchema = jpaRepository.save(existingSchema);
            logger.info("An existing entity of class {} is being updated in the system!", entity.getClass().getSimpleName());
            return mapper.toEntity(savedSchema);
        } catch (Exception e) {
            logger.error("Failed to update entity: {}", entity, e);
            throw new RepositoryException("Repository: Failed to update entity", e);
        }
    }

    /**
     * Sync version of delete method.
     *
     * @param id Reference for entity to be deleted.
     * @throws RepositoryException Thrown when an unexpected database error occurs.
     * @author Lucas Batista Pereira
     * @since 06/11/2024
     */
    @Override
    public void deleteSync(@NotNull UUID id, @NotNull RepositoryAuth auth) throws RepositoryException {
        try {
            this.delete(id, auth);
        } catch (Exception e) {
            logger.error("Failed to delete entity with id: {}", id, e);
            throw new RepositoryException("Repository: Failed to delete entity", e);
        }
    }

    protected void delete(@NotNull UUID id, @NotNull RepositoryAuth auth) {
        S schema = this.readInternal(id, auth);

        schema.setDeleted(true);
        schema.setDeletedAt(LocalDateTime.now());
        jpaRepository.save(schema);

        logger.info("Entity with id: {} was marked as deleted.", id);
    }

    /**
     * Find all entities of given entity class.
     *
     * @return Optional<List < E>>
     * @throws RepositoryException Thrown when an unexpected database error occurs.
     * @author Lucas Batista Pereira
     * @since 06/11/2024
     */
    @Override
    public Optional<List<E>> findAll(@NotNull RepositoryAuth auth) throws RepositoryException {
        try {
            List<S> schemas = this.findAll(auth.user_id(), auth.company_id(), entityClass);
            List<E> entities = schemas.stream()
                    .map(mapper::toEntity)
                    .collect(Collectors.toList());
            return entities.isEmpty() ? Optional.empty() : Optional.of(entities);
        } catch (Exception e) {
            logger.error("Failed to find all entities for user_id: {} and company_id: {}", auth.user_id(), auth.company_id(), e);
            throw new RepositoryException("Repository: Failed to find all entities", e);
        }
    }

    /**
     * Find all entities by given ids.
     *
     * @param ids Reference ids for reading.
     * @return Optional<List < E>>
     * @throws RepositoryException Thrown when an unexpected database error occurs.
     * @author Lucas Batista Pereira
     * @since 06/11/2024
     */
    @Override
    public Optional<List<E>> findAllByIds(@NotNull List<UUID> ids, @NotNull RepositoryAuth auth) throws RepositoryException {
        try {
            Set<UUID> idSet = new HashSet<>(ids);
            List<S> schemas = this.findAllByIds(auth.user_id(), auth.company_id(), idSet, entityClass);
            List<E> entities = schemas.stream()
                    .map(mapper::toEntity)
                    .collect(Collectors.toList());
            return entities.isEmpty() ? Optional.empty() : Optional.of(entities);
        } catch (Exception e) {
            logger.error("Failed to find entities by IDs: {}", ids, e);
            throw new RepositoryException("Repository: Failed to find entities by IDs", e);
        }
    }

    /**
     * Check the existence of an entity given its id.
     *
     * @param id Reference id for checking.
     * @throws RepositoryException Thrown when an unexpected database error occurs.
     * @author Lucas Batista Pereira
     * @since 06/11/2024
     */
    @Override
    public void check(@NotNull UUID id, @NotNull RepositoryAuth auth) throws RepositoryException {
        this.readInternal(id, auth);
    }

    /**
     * Check the existence of all entities given its ids.
     *
     * @param ids Reference ids for checking.
     * @throws RepositoryException Thrown when an unexpected database error occurs.
     * @author Lucas Batista Pereira
     * @since 06/11/2024
     */
    @Override
    public void checkAll(@NotNull Set<UUID> ids, @NotNull RepositoryAuth auth) throws RepositoryException {
        List<S> schemas = this.findAllByIds(auth.user_id(), auth.company_id(), ids, entityClass);
        if (schemas.size() != ids.size()) {
            Set<UUID> foundIds = schemas.stream().map(S::getId).collect(Collectors.toSet());
            Set<UUID> missingIds = ids.stream().filter(id -> !foundIds.contains(id)).collect(Collectors.toSet());
            throw new RepositoryEntityNotFoundException("Repository: Entities not found or already deleted for IDs: " + missingIds);
        }
    }

    /**
     * Find all entities paginated.
     *
     * @param pageRequest PageRequest object.
     * @return Page<E>
     * @throws RepositoryException Thrown when an unexpected database error occurs.
     * @since 06/11/2024
     */
    public Page<E> findAllPaginated(PageRequest pageRequest, @NotNull RepositoryAuth auth) throws RepositoryException {
        try {
            return this.findAllPaginated(auth.user_id(), auth.company_id(), pageRequest, entityClass)
                    .map(mapper::toEntity);
        } catch (Exception e) {
            logger.error("Failed to find entities paginated");
            throw new RepositoryException("Repository: Failed to find entities by IDs", e);
        }
    }
}