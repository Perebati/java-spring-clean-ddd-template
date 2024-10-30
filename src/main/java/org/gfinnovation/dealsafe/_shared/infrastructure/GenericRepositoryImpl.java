package org.gfinnovation.dealsafe._shared.infrastructure;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.gfinnovation.dealsafe._shared.entity.GenericEntity;
import org.gfinnovation.dealsafe._shared.entity.GenericRepository;
import org.gfinnovation.dealsafe.configuration.exception.models.EntityNotFoundException;
import org.gfinnovation.dealsafe.configuration.exception.models.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class GenericRepositoryImpl
 * @authorNote To avoid the need to implement a CRUD operation on every single
 * repository in this system, each repository implementation should extend
 * from this class.
 * @since 30/10/2024
 */
@RequiredArgsConstructor
public class GenericRepositoryImpl<E extends GenericEntity, S extends GenericSchema>
        implements GenericRepository<E> {

    private static final Logger logger = LoggerFactory.getLogger(GenericRepositoryImpl.class);
    private final GenericMapper<E, S> mapper;
    private final SimpleJpaRepository<S, UUID> jpaRepository;

    @Transactional
    public E create(E entity) {
        try {
            S schema = mapper.toSchema(entity);
            schema.setCreatedAt(LocalDateTime.now());
            schema.setDeleted(false);
            S savedSchema = jpaRepository.save(schema);
            return mapper.toEntity(savedSchema);
        } catch (DataAccessException e) {
            logger.error("Failed to save entity: {}", entity, e);
            throw new RepositoryException("Failed to save entity", e);
        }
    }

    @Transactional
    public Optional<E> read(UUID id) {
        try {
            return jpaRepository.findById(id)
                    .filter(schema -> !schema.getDeleted())
                    .map(mapper::toEntity);
        } catch (DataAccessException e) {
            logger.error("Failed to retrieve entity with id: {}", id, e);
            throw new RepositoryException("Failed to retrieve data", e);
        }
    }

    @Transactional
    public E update(E entity) {
        try {
            return jpaRepository.findById(entity.getId())
                    .filter(existing -> !existing.getDeleted())
                    .map(existing -> {
                        S updatedSchema = mapper.toSchema(entity);
                        updatedSchema.setCreatedAt(existing.getCreatedAt());
                        updatedSchema.setDeleted(existing.getDeleted());
                        updatedSchema.setDeletedAt(existing.getDeletedAt());
                        updatedSchema.setUpdatedAt(LocalDateTime.now());
                        return jpaRepository.save(updatedSchema);
                    })
                    .map(mapper::toEntity)
                    .orElseThrow(() -> new EntityNotFoundException("Entity not found or deleted"));
        } catch (DataAccessException e) {
            logger.error("Failed to update entity: {}", entity, e);
            throw new org.gfinnovation.dealsafe.configuration.exception.models.RepositoryException("Failed to update entity", e);
        }
    }

    @Transactional
    public void delete(UUID id) {
        try {
            S schema = jpaRepository.findById(id)
                    .filter(entity -> !entity.getDeleted())
                    .orElseThrow(() -> new EntityNotFoundException("Entity not found or already deleted"));
            schema.setDeleted(true);
            schema.setDeletedAt(LocalDateTime.now());
            jpaRepository.save(schema);
        } catch (DataAccessException e) {
            logger.error("Failed to delete entity with id: {}", id, e);
            throw new org.gfinnovation.dealsafe.configuration.exception.models.RepositoryException("Failed to delete entity", e);
        }
    }

    @Transactional
    public Optional<List<E>> findAll() {
        try {
            List<E> entities = jpaRepository.findAll().stream()
                    .filter(schema -> !schema.getDeleted())
                    .map(mapper::toEntity)
                    .toList();
            return entities.isEmpty() ? Optional.empty() : Optional.of(entities);
        } catch (DataAccessException e) {
            logger.error("Failed to find all entities", e);
            throw new org.gfinnovation.dealsafe.configuration.exception.models.RepositoryException("Failed to find all entities", e);
        }
    }

    @Transactional
    public Optional<List<E>> findAllByIds(List<UUID> ids) {
        try {
            List<E> entities = jpaRepository.findAllById(ids).stream()
                    .filter(schema -> !schema.getDeleted())
                    .map(mapper::toEntity)
                    .toList();
            return entities.isEmpty() ? Optional.empty() : Optional.of(entities);
        } catch (DataAccessException e) {
            logger.error("Failed to find entities by IDs: {}", ids, e);
            throw new org.gfinnovation.dealsafe.configuration.exception.models.RepositoryException("Failed to find entities by IDs", e);
        }
    }

    @Transactional
    public void check(UUID id) {
        if (read(id).isEmpty()) {
            throw new EntityNotFoundException("Entity not found or deleted");
        }
    }

    @Transactional
    public void checkAll(Set<UUID> ids) {
        ids.forEach(this::check);
    }
}