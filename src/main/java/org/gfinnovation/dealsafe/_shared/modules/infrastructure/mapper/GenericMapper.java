package org.gfinnovation.dealsafe._shared.modules.infrastructure.mapper;


import org.gfinnovation.dealsafe._shared.modules.domain.GenericClass;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.GenericEntity;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * This system uses MapStruct for transforming Entities to Schemas and vice-versa.
 * To avoid boilerplate code, every single Mapper in this system should extend from
 * this one.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface GenericMapper
 * @since 30/10/2024
 */

public interface GenericMapper<E extends GenericClass, S extends GenericEntity> {

    E toEntity(S schema);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    S toSchema(E entity);

    default List<E> toEntityList(List<S> schemaList) {
        return schemaList.stream().map(this::toEntity).toList();
    }

    default List<S> toSchemaList(List<E> entityList) {
        return entityList.stream().map(this::toSchema).toList();
    }
}
