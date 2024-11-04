package org.gfinnovation.dealsafe.domains.tree.infrastructure.persistence.mapper;

import org.gfinnovation.dealsafe._shared.infrastructure.GenericMapper;
import org.gfinnovation.dealsafe.domains.tree.entity.NodeTreeEntity;
import org.gfinnovation.dealsafe.domains.tree.infrastructure.persistence.NodeTreeSchema;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface NodeTreeMapper
 * @since 30/10/2024
 */

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface NodeTreeMapper extends GenericMapper<NodeTreeEntity, NodeTreeSchema> {
}