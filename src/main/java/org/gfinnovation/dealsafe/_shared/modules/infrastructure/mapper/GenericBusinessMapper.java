package org.gfinnovation.dealsafe._shared.modules.infrastructure.mapper;

import org.gfinnovation.dealsafe._shared.modules.domain.GenericClass;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.GenericEntity;
import org.mapstruct.Mapping;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface GenericBusinessMapper
 * @since 06/11/2024
 */

public interface GenericBusinessMapper<E extends GenericClass, S extends GenericEntity> {
    E toEntity(S schema);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "user_id", ignore = true)
    @Mapping(target = "company_id", ignore = true)
    S toSchema(E entity);

    default List<E> toEntityList(List<S> schemaList) {
        if (schemaList == null) {
            return null;
        }
        return schemaList.stream()
                .map(this::toEntity)
                .collect(toList());
    }

    default List<S> toSchemaList(List<E> entityList) {
        if (entityList == null) {
            return null;
        }
        return entityList.stream()
                .map(this::toSchema)
                .collect(toList());
    }
}