package org.gfinnovation.dealsafe._shared.infrastructure;

import jakarta.transaction.Transactional;
import org.gfinnovation.dealsafe._shared.entity.GenericBusinessEntity;
import org.gfinnovation.dealsafe._shared.entity.GenericBusinessRepository;
import org.gfinnovation.dealsafe.configuration.exception.models.EntityNotFoundException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        extends GenericBusinessJpaRepository<S> implements GenericBusinessRepository<E> {

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
     * @param schema     Reference schema.
     * @param user_id    Reference for user that made an infra operation.
     * @param company_id Reference for company that made an infra operation.
     * @author Lucas Batista Pereira
     * @since 06/11/2024
     */

    private void setCommonFields(S schema, UUID user_id, UUID company_id) {
        schema.setUser_id(user_id);
        schema.setCompany_id(company_id);
    }


    /**
     * In the future, creation will be done on a remote database using
     * kafka queues, that's why it needs to be async.
     *
     * @param user_id    Reference for user that made an infra operation.
     * @param company_id Reference for company that made an infra operation.
     * @param entity     Entity that extends GenericBusinessEntity
     * @return CompletableFuture<E>
     * @throws RepositoryException Thrown when an unexpected database error occurs.
     * @author Lucas Batista Pereira
     * @since 06/11/2024
     */

    @Override
    @Async
    @Transactional
    public CompletableFuture<E> create(UUID user_id, UUID company_id, E entity) throws RepositoryException {
        try {
            S schema = mapper.toSchema(entity);
            setCommonFields(schema, user_id, company_id);
            S savedSchema = jpaRepository.save(schema);
            logger.info("A new {} was created in the system!", entity.getClass().getSimpleName());
            return CompletableFuture.completedFuture(mapper.toEntity(savedSchema));
        } catch (DataAccessException e) {
            logger.error("Failed to save entity: {}", entity, e);
            throw new RepositoryException("Failed to save entity", e);
        }
    }


    /**
     * Standard reading method.
     *
     * @param user_id    Reference for user that made an infra operation.
     * @param company_id Reference for company that made an infra operation.
     * @param id         Reference entity id.
     * @return Entity
     * @throws RepositoryException     Thrown when an unexpected database error occurs.
     * @throws EntityNotFoundException Thrown when an entity is not found!
     * @author Lucas Batista Pereira
     * @since 06/11/2024
     */

    @Override
    @Transactional
    public E read(UUID user_id, UUID company_id, UUID id) throws RepositoryException, EntityNotFoundException {
        try {
            return this.findByIdCompanyIdUserIdAndNotDeleted(id, company_id, user_id, entityClass)
                    .map(mapper::toEntity)
                    .orElseThrow(() -> new EntityNotFoundException("Entity not found or already deleted with id: " + id));
        } catch (DataAccessException e) {
            logger.error("Failed to retrieve entity with id: {}", id, e);
            throw new RepositoryException("Failed to retrieve data", e);
        }
    }


    /**
     * Protected class, for internal use only.
     *
     * @param user_id    Reference for user that made an infra operation.
     * @param company_id Reference for company that made an infra operation.
     * @param id         Reference entity id.
     * @return Schema
     * @throws RepositoryException     Thrown when an unexpected database error occurs.
     * @throws EntityNotFoundException Thrown when an entity is not found!
     * @author Lucas Batista Pereira
     * @since 06/11/2024
     */

    @Transactional
    protected S readInternal(UUID user_id, UUID company_id, UUID id) throws RepositoryException, EntityNotFoundException {
        try {
            return this.findByIdCompanyIdUserIdAndNotDeleted(id, company_id, user_id, entityClass)
                    .orElseThrow(() -> new EntityNotFoundException("Entity not found or already deleted with id: " + id));
        } catch (DataAccessException e) {
            logger.error("Failed to retrieve entity with id: {}", id, e);
            throw new RepositoryException("Failed to retrieve data", e);
        }
    }


    /**
     * Update a schema based on an entity.
     *
     * @param user_id    Reference for user that made an infra operation.
     * @param company_id Reference for company that made an infra operation.
     * @param entity     Entity to be updated.
     * @return CompletableFuture<E>
     * @throws RepositoryException Thrown when an unexpected database error occurs.
     * @author Lucas Batista Pereira
     * @since 06/11/2024
     */

    @Override
    @Async
    @Transactional
    public CompletableFuture<E> update(UUID user_id, UUID company_id, E entity) throws RepositoryException {
        try {
            this.readInternal(user_id, company_id, entity.getId());
            S existingSchema = mapper.toSchema(entity);
            S savedSchema = jpaRepository.save(existingSchema);
            logger.info("An existing entity of class {} is being updated in the system!", entity.getClass().getSimpleName());
            return CompletableFuture.completedFuture(mapper.toEntity(savedSchema));
        } catch (DataAccessException e) {
            logger.error("Failed to update entity: {}", entity, e);
            throw new RepositoryException("Failed to update entity", e);
        }
    }


    /**
     * Soft delete an entity.
     *
     * @param user_id    Reference for user that made an infra operation.
     * @param company_id Reference for company that made an infra operation.
     * @param id         Reference for entity to be deleted.
     * @throws RepositoryException Thrown when an unexpected database error occurs.
     * @author Lucas Batista Pereira
     * @since 06/11/2024
     */

    @Override
    @Async
    @Transactional
    public void delete(UUID user_id, UUID company_id, UUID id) throws RepositoryException {
        try {
            S schema = this.readInternal(user_id, company_id, id);

            schema.setDeleted(true);
            schema.setDeletedAt(LocalDateTime.now());
            jpaRepository.save(schema);

            logger.info("Entity with id: {} was marked as deleted.", id);
        } catch (DataAccessException e) {
            logger.error("Failed to delete entity with id: {}", id, e);
            throw new RepositoryException("Failed to delete entity", e);
        }
    }


    /**
     * Find all entities of given entity class.
     *
     * @param user_id    Reference for user that made an infra operation.
     * @param company_id Reference for company that made an infra operation.
     * @return Optional<List < E>>
     * @throws RepositoryException Thrown when an unexpected database error occurs.
     * @author Lucas Batista Pereira
     * @since 06/11/2024
     */

    @Override
    @Transactional
    public Optional<List<E>> findAll(UUID user_id, UUID company_id) throws RepositoryException {
        try {
            List<S> schemas = this.findAllByUserIdAndCompanyIdAndDeletedFalse(user_id, company_id, entityClass);
            List<E> entities = schemas.stream()
                    .map(mapper::toEntity)
                    .collect(Collectors.toList());
            return entities.isEmpty() ? Optional.empty() : Optional.of(entities);
        } catch (DataAccessException e) {
            logger.error("Failed to find all entities for user_id: {} and company_id: {}", user_id, company_id, e);
            throw new RepositoryException("Failed to find all entities", e);
        }
    }


    /**
     * Find all entities by given ids.
     *
     * @param user_id    Reference for user that made an infra operation.
     * @param company_id Reference for company that made an infra operation.
     * @param ids        Reference ids for reading.
     * @return Optional<List < E>>
     * @throws RepositoryException Thrown when an unexpected database error occurs.
     * @author Lucas Batista Pereira
     * @since 06/11/2024
     */

    @Override
    @Transactional
    public Optional<List<E>> findAllByIds(UUID user_id, UUID company_id, List<UUID> ids) throws RepositoryException {
        try {
            Set<UUID> idSet = new HashSet<>(ids);
            List<S> schemas = this.findAllByUserIdAndCompanyIdAndIdInAndDeletedFalse(user_id, company_id, idSet, entityClass);
            List<E> entities = schemas.stream()
                    .map(mapper::toEntity)
                    .collect(Collectors.toList());
            return entities.isEmpty() ? Optional.empty() : Optional.of(entities);
        } catch (DataAccessException e) {
            logger.error("Failed to find entities by IDs: {}", ids, e);
            throw new RepositoryException("Failed to find entities by IDs", e);
        }
    }


    /**
     * Check the existence of an entity given its id.
     *
     * @param user_id    Reference for user that made an infra operation.
     * @param company_id Reference for company that made an infra operation.
     * @param id         Reference id for checking.
     * @throws RepositoryException Thrown when an unexpected database error occurs.
     * @author Lucas Batista Pereira
     * @since 06/11/2024
     */

    @Override
    @Transactional
    public void check(UUID user_id, UUID company_id, UUID id) throws RepositoryException {
        this.readInternal(user_id, company_id, id);
    }


    /**
     * Check the existence of all entities given its ids.
     *
     * @param user_id    Reference for user that made an infra operation.
     * @param company_id Reference for company that made an infra operation.
     * @param ids        Reference ids for checking.
     * @throws RepositoryException Thrown when an unexpected database error occurs.
     * @author Lucas Batista Pereira
     * @since 06/11/2024
     */

    @Override
    @Transactional
    public void checkAll(UUID user_id, UUID company_id, Set<UUID> ids) throws RepositoryException {
        List<S> schemas = this.findAllByUserIdAndCompanyIdAndIdInAndDeletedFalse(user_id, company_id, ids, entityClass);
        if (schemas.size() != ids.size()) {
            Set<UUID> foundIds = schemas.stream().map(S::getId).collect(Collectors.toSet());
            Set<UUID> missingIds = ids.stream().filter(id -> !foundIds.contains(id)).collect(Collectors.toSet());
            throw new EntityNotFoundException("Entities not found or already deleted for IDs: " + missingIds);
        }
    }
}