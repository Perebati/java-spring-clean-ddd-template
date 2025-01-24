package org.gfinnovation.dealsafe._shared.modules.application;

import org.gfinnovation.dealsafe._shared.modules.domain.GenericClass;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.GenericEntity;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class GenericBusiness
 * Deprecated. It still exists only because the idea behind this is ingenious.
 * @since 30/10/2024
 */

@Deprecated
public class GenericBusinessDeprecated<E extends GenericClass, S extends GenericEntity> {
//    private final GenericMapper<E, S> mapper;
//    private final GenericRepository<S> repository;
//
//    public GenericBusiness(GenericMapper<E, S> mapper, GenericRepository<S> repository) {
//        this.mapper = mapper;
//        this.repository = repository;
//    }
//
//    @Transactional
//    public E create(E entity) {
//        S schema = mapper.toSchema(entity);
//        S schemaResult = repository.create(schema);
//        return mapper.toEntity(schemaResult);
//    }
//
//    public Optional<E> read(UUID id) {
//        return repository.read(id)
//                .map(mapper::toEntity);
//    }
//
//    @Transactional
//    public E update(E entity) {
//        S schema = mapper.toSchema(entity);
//        return mapper.toEntity(repository.update(schema));
//    }
//
//    @Transactional
//    public void delete(UUID id) {
//        repository.delete(id);
//    }
//
//    public Optional<List<E>> findAll() {
//        List<E> entities = repository.findAll().stream()
//                .filter(schema -> !schema.getDeleted())
//                .map(mapper::toEntity)
//                .collect(Collectors.toList());
//
//        return entities.isEmpty() ? Optional.empty() : Optional.of(entities);
//    }
//
//    public Optional<List<E>> findAllByIds(List<UUID> ids) {
//        List<E> entities = repository.findAll().stream()
//                .filter(schema -> !schema.getDeleted() && ids.contains(schema.getId()))
//                .map(mapper::toEntity)
//                .collect(Collectors.toList());
//
//        return entities.isEmpty() ? Optional.empty() : Optional.of(entities);
//    }
//
//    public void check(UUID id) {
//        read(id).orElseThrow(() ->
//                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found or has been deleted")
//        );
//    }
//
//    public void check(Set<UUID> ids) {
//        ids.forEach(id ->
//                read(id).orElseThrow(() ->
//                        new ResponseStatusException(HttpStatus.NOT_FOUND,
//                                String.format("Entity with ID %s not found or has been deleted", id))
//                )
//        );
//    }
}
