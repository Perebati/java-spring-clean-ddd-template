package org.gfinnovation.dealsafe.authentication.user.infrastructure.persistence.mapper;

import org.gfinnovation.dealsafe._shared.infrastructure.GenericMapper;
import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;
import org.gfinnovation.dealsafe.authentication.user.infrastructure.persistence.UserSchema;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface UserMapper
 * @authorNote n/a
 * @since 30/10/2024
 */
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper extends GenericMapper<UserEntity, UserSchema> {
}
