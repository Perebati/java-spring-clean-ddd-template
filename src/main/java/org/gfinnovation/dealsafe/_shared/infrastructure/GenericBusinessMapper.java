package org.gfinnovation.dealsafe._shared.infrastructure;

import org.gfinnovation.dealsafe._shared.entity.GenericEntity;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface GenericBusinessMapper
 * @since 06/11/2024
 */

public interface GenericBusinessMapper<E extends GenericEntity, S extends GenericSchema> {
    E toEntity(S schema);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "user_id", ignore = true)
    @Mapping(target = "company_id", ignore = true)
    S toSchema(E entity);

    default List<E> toEntityList(List<S> schemaList) {
        return schemaList.stream().map(this::toEntity).toList();
    }

    default List<S> toSchemaList(List<E> entityList) {
        return entityList.stream().map(this::toSchema).toList();
    }
}