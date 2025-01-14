package org.gfinnovation.dealsafe.modules.tree.infrastructure.mapper;

import org.gfinnovation.dealsafe._shared.modules.infrastructure.mapper.GenericBusinessMapper;
import org.gfinnovation.dealsafe.modules.tree.domain.valueobjects.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.infrastructure.RootTreeDynamicEntity;
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
public interface RootTreeDynamicMapper extends GenericBusinessMapper<RootTreeDynamic, RootTreeDynamicEntity> {
}
