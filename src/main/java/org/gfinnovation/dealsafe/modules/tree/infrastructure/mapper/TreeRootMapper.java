package org.gfinnovation.dealsafe.modules.tree.infrastructure.mapper;

import org.gfinnovation.dealsafe._shared.modules.infrastructure.mapper.GenericBusinessMapper;
import org.gfinnovation.dealsafe.modules.tree.domain.TreeRoot;
import org.gfinnovation.dealsafe.modules.tree.infrastructure.TreeRootSchema;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface TreeRootMapper
 * @since 30/10/2024
 */

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TreeRootMapper extends GenericBusinessMapper<TreeRoot, TreeRootSchema> {
    @Override
    @Mapping(target = "nodes", source = "nodes")
    TreeRoot toEntity(TreeRootSchema schema);

    @Override
    @Mapping(target = "nodes", source = "nodes")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "user_id", ignore = true)
    @Mapping(target = "company_id", ignore = true)
    TreeRootSchema toSchema(TreeRoot entity);
}