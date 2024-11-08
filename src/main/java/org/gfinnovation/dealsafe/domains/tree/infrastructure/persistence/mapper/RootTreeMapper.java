package org.gfinnovation.dealsafe.domains.tree.infrastructure.persistence.mapper;

import org.gfinnovation.dealsafe._shared.infrastructure.persistence.mapper.GenericBusinessMapper;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeEntity;
import org.gfinnovation.dealsafe.domains.tree.infrastructure.persistence.RootTreeSchema;
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
public interface RootTreeMapper extends GenericBusinessMapper<RootTreeEntity, RootTreeSchema> {
    @Override
    @Mapping(target = "nodes", source = "nodes")
    RootTreeEntity toEntity(RootTreeSchema schema);

    @Override
    @Mapping(target = "nodes", source = "nodes")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "user_id", ignore = true)
    @Mapping(target = "company_id", ignore = true)
    RootTreeSchema toSchema(RootTreeEntity entity);
}