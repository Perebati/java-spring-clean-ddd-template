package org.gfinnovation.dealsafe._shared.modules.infrastructure.mapper;

import org.gfinnovation.dealsafe._shared.modules.domain.GenericEntity;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.GenericSchema;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface GenericBusinessMapper
 * @since 06/11/2024
 */

public interface GenericBusinessMapper<E extends GenericEntity, S extends GenericSchema> {
    E toEntity(S schema);
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