package org.gfinnovation.dealsafe.domains.tree.infrastructure.persistence.mapper;

import org.gfinnovation.dealsafe._shared.infrastructure.GenericBusinessMapper;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeDynamicEntity;
import org.gfinnovation.dealsafe.domains.tree.infrastructure.persistence.RootTreeDynamicSchema;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface RootTreeDynamicMapper
 * @since 30/10/2024
 */

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RootTreeDynamicMapper extends GenericBusinessMapper<RootTreeDynamicEntity, RootTreeDynamicSchema> {
}
