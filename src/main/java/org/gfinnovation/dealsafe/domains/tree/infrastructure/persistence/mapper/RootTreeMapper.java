package org.gfinnovation.dealsafe.domains.tree.infrastructure.persistence.mapper;

import org.gfinnovation.dealsafe._shared.infrastructure.GenericMapper;
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
public interface RootTreeMapper extends GenericMapper<RootTreeEntity, RootTreeSchema> {
    @Override
    @Mapping(target = "nodes", source = "nodes")
    RootTreeEntity toEntity(RootTreeSchema schema);

    @Override
    @Mapping(target = "nodes", source = "nodes")
    RootTreeSchema toSchema(RootTreeEntity entity);
}