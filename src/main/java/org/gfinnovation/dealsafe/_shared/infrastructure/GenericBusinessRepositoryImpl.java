package org.gfinnovation.dealsafe._shared.infrastructure;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.gfinnovation.dealsafe._shared.entity.GenericBusinessEntity;
import org.gfinnovation.dealsafe._shared.entity.GenericBusinessRepository;
import org.gfinnovation.dealsafe.configuration.exception.models.EntityNotFoundException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/*
 * TODO: Need revision!
 * TODO: Use MDC for getting company_id and user_id instead of passing the args through method header manually.
 * TODO: Use native JPA structures to keep track of creation, update and deletion dateTimes.
 * TODO: Try to optimize the way how company_id and user_id are being filtered on read functions.
 */

/**
 * This is a business version of GenericRepositoryImpl.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class GenericBusinessRepositoryImpl
 * @since 01/11/2024
 */

@RequiredArgsConstructor
public class GenericBusinessRepositoryImpl<E extends GenericBusinessEntity, S extends GenericBusinessSchema>
        implements GenericBusinessRepository<E> {

    private static final Logger logger = LoggerFactory.getLogger(GenericRepositoryImpl.class);
    private final GenericMapper<E, S> mapper;
    private final SimpleJpaRepository<S, UUID> jpaRepository;


    /**
     * Handles entity mapping and creation.
     *
     * @param entity Generic entity.
     * @return Entity.
     * @throws RepositoryException Thrown when that an error on the database level occurs.
     * @author Lucas Batista Pereira
     * @since 01/11/2024
     */

    @Transactional
    public E create(UUID user_id, UUID company_id, E entity) {
        try {
            S schema = mapper.toSchema(entity);
            schema.setCreatedAt(LocalDateTime.now());
            schema.setDeleted(false);
            schema.setUserId(user_id);
            schema.setCompanyId(company_id);
            S savedSchema = jpaRepository.save(schema);
            logger.info("A new " + entity.getClass().getName() + " were created in the system!");
            return mapper.toEntity(savedSchema);
        } catch (DataAccessException e) {
            logger.error("Failed to save entity: {}", entity, e);
            throw new RepositoryException("Failed to save entity", e);
        }
    }

    /**
     * Handles entity mapping and reading via id.
     *
     * @param id Generic entityId.
     * @return Optional Entity.
     * @throws RepositoryException Thrown when that an error on the database level occurs.
     * @author Lucas Batista Pereira
     * @since 01/11/2024
     */

    @Transactional
    public Optional<E> read(UUID user_id, UUID company_id, UUID id) {
        try {
            return jpaRepository.findById(id)
                    .filter(schema -> !schema.getDeleted())
                    .filter(schema -> schema.getCompanyId().equals(company_id))
                    .filter(schema -> schema.getUserId().equals(user_id))
                    .map(mapper::toEntity);
        } catch (DataAccessException e) {
            logger.error("Failed to retrieve entity with id: {}", id, e);
            throw new RepositoryException("Failed to retrieve data", e);
        }
    }

    /**
     * Handles entity mapping and reading via id, for internal use only.
     *
     * @param id Generic entityId.
     * @return Optional schema.
     * @throws RepositoryException Thrown when that an error on the database level occurs.
     * @author Lucas Batista Pereira
     * @since 01/11/2024
     */

    @Transactional()
    protected Optional<S> readInternal(UUID user_id, UUID company_id, UUID id) {
        try {
            return jpaRepository.findById(id)
                    .filter(schema -> !schema.getDeleted())
                    .filter(schema -> schema.getCompanyId().equals(company_id))
                    .filter(schema -> schema.getUserId().equals(user_id));
        } catch (DataAccessException e) {
            logger.error("Failed to retrieve entity with id: {}", id, e);
            throw new RepositoryException("Failed to retrieve data", e);
        }
    }

    /**
     * Handles entity mapping and updating.
     *
     * @param entity Generic entity.
     * @return Entity.
     * @throws EntityNotFoundException Thrown when an entity doesn't existis in db.
     * @throws RepositoryException     Thrown when that an error on the database level occurs.
     * @author Lucas Batista Pereira
     * @since 01/11/2024
     */

    @Transactional
    public E update(UUID user_id, UUID company_id, E entity) {
        try {
            logger.info("An existing entity of class " + entity.getClass().getName() + " is being updated in the system!");
            return this.readInternal(user_id, company_id, entity.getId())
                    .filter(existing -> !existing.getDeleted())
                    .map(existing -> {
                        S updatedSchema = mapper.toSchema(entity);
                        updatedSchema.setCreatedAt(existing.getCreatedAt());
                        updatedSchema.setDeleted(existing.getDeleted());
                        updatedSchema.setDeletedAt(existing.getDeletedAt());
                        updatedSchema.setCompanyId(existing.getCompanyId());
                        updatedSchema.setUserId(existing.getUserId());
                        updatedSchema.setUpdatedAt(LocalDateTime.now());
                        return jpaRepository.save(updatedSchema);
                    })
                    .map(mapper::toEntity)
                    .orElseThrow(() -> new EntityNotFoundException("Entity not found or deleted"));
        } catch (DataAccessException e) {
            logger.error("Failed to update entity: {}", entity, e);
            throw new RepositoryException("Failed to update entity", e);
        }
    }

    /**
     * Handles entity deletion via id.
     *
     * @param id Generic entityId.
     * @throws EntityNotFoundException Thrown when an entity doesn't existis in db.
     * @throws RepositoryException     Thrown when that an error on the database level occurs.
     * @author Lucas Batista Pereira
     * @since 01/11/2024
     */

    @Transactional
    public void delete(UUID user_id, UUID company_id, UUID id) {
        try {
            S schema = this.readInternal(user_id, company_id, id)
                    .filter(entity -> !entity.getDeleted())
                    .orElseThrow(() -> new EntityNotFoundException("Entity not found or already deleted"));
            schema.setDeleted(true);
            schema.setDeletedAt(LocalDateTime.now());
            jpaRepository.save(schema);
        } catch (DataAccessException e) {
            logger.error("Failed to delete entity with id: {}", id, e);
            throw new RepositoryException("Failed to delete entity", e);
        }
    }

    /**
     * Handles entity reading and mapping of all entities given its type.
     *
     * @return Optional List of Entities.
     * @throws RepositoryException Thrown when that an error on the database level occurs.
     * @author Lucas Batista Pereira
     * @since 01/11/2024
     */

    @Transactional
    public Optional<List<E>> findAll(UUID user_id, UUID company_id) {
        try {
            List<E> entities = jpaRepository.findAll().stream()
                    .filter(schema -> !schema.getDeleted())
                    .filter(schema -> schema.getCompanyId().equals(company_id))
                    .filter(schema -> schema.getUserId().equals(user_id))
                    .map(mapper::toEntity)
                    .toList();
            return entities.isEmpty() ? Optional.empty() : Optional.of(entities);
        } catch (DataAccessException e) {
            logger.error("Failed to find all entities", e);
            throw new RepositoryException("Failed to find all entities", e);
        }
    }

    /**
     * Handles entity reading and mapping of all entities given its ids.
     *
     * @return Optional List of Entities.
     * @throws RepositoryException Thrown when that an error on the database level occurs.
     * @author Lucas Batista Pereira
     * @since 01/11/2024
     */

    @Transactional
    public Optional<List<E>> findAllByIds(UUID user_id, UUID company_id, List<UUID> ids) {
        try {
            List<E> entities = jpaRepository.findAllById(ids).stream()
                    .filter(schema -> !schema.getDeleted())
                    .filter(schema -> schema.getCompanyId().equals(company_id))
                    .filter(schema -> schema.getUserId().equals(user_id))
                    .map(mapper::toEntity)
                    .toList();
            return entities.isEmpty() ? Optional.empty() : Optional.of(entities);
        } catch (DataAccessException e) {
            logger.error("Failed to find entities by IDs: {}", ids, e);
            throw new RepositoryException("Failed to find entities by IDs", e);
        }
    }

    /**
     * Handles entity checking via id.
     *
     * @throws EntityNotFoundException Thrown when an entity doesn't existis in db.
     * @author Lucas Batista Pereira
     * @since 01/11/2024
     */

    @Transactional
    public void check(UUID user_id, UUID company_id, UUID id) {
        if (readInternal(user_id, company_id, id).isEmpty()) {
            throw new EntityNotFoundException("Entity not found or deleted");
        }
    }

    /**
     * Handles entity checking via a set of ids.
     *
     * @throws EntityNotFoundException Thrown when an entity doesn't existis in db.
     * @author Lucas Batista Pereira
     * @since 01/11/2024
     */

    @Transactional
    public void checkAll(UUID user_id, UUID company_id, Set<UUID> ids) {
        for (UUID id : ids) this.check(user_id, company_id, id);
    }
}