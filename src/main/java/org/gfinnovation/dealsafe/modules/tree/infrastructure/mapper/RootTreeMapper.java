package org.gfinnovation.dealsafe.modules.tree.infrastructure.mapper;

import org.gfinnovation.dealsafe._shared.modules.infrastructure.mapper.GenericBusinessMapper;
import org.gfinnovation.dealsafe.modules.tree.domain.RootTree;
import org.gfinnovation.dealsafe.modules.tree.infrastructure.RootTreeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface RootTreeMapper
 * @since 30/10/2024
 */

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RootTreeMapper extends GenericBusinessMapper<RootTree, RootTreeEntity> {
    @Override
    @Mapping(target = "nodes", source = "nodes")
    RootTree toEntity(RootTreeEntity schema);

    @Override
    @Mapping(target = "nodes", source = "nodes")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "user_id", ignore = true)
    @Mapping(target = "company_id", ignore = true)
    RootTreeEntity toSchema(RootTree entity);
}