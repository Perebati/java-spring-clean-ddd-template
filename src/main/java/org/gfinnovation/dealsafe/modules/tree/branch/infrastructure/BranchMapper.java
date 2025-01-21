package org.gfinnovation.dealsafe.modules.tree.branch.infrastructure;

import org.gfinnovation.dealsafe._shared.modules.infrastructure.mapper.GenericBusinessMapper;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface BranchMapper
 * @since 20/01/2025
 */

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface BranchMapper extends GenericBusinessMapper<Branch, BranchEntity> {
}