package org.gfinnovation.dealsafe.domains.tree.infrastructure.persistence.mapper;

import org.gfinnovation.dealsafe._shared.infrastructure.persistence.mapper.GenericBusinessMapper;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeStaticEntity;
import org.gfinnovation.dealsafe.domains.tree.infrastructure.persistence.RootTreeStaticSchema;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface RootTreeStaticMapper
 * @since 30/10/2024
 */

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RootTreeStaticMapper extends GenericBusinessMapper<RootTreeStaticEntity, RootTreeStaticSchema> {
}
