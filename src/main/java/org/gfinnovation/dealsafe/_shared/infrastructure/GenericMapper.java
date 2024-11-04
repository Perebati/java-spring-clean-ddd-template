package org.gfinnovation.dealsafe._shared.infrastructure;


import org.gfinnovation.dealsafe._shared.entity.GenericEntity;

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

public interface GenericMapper<E extends GenericEntity, S extends GenericSchema> {
    E toEntity(S schema);

    S toSchema(E entity);

    default List<E> toEntityList(List<S> schemaList) {
        return schemaList.stream().map(this::toEntity).toList();
    }

    default List<S> toSchemaList(List<E> entityList) {
        return entityList.stream().map(this::toSchema).toList();
    }
}
