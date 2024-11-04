package org.gfinnovation.dealsafe.domains.operation.application.business.components.interfaces;

import jakarta.validation.ValidationException;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.application.GenericBusiness;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.domains.operation.entity.ComparisonOperationEntity;
import org.gfinnovation.dealsafe.domains.operation.entity.comparison.ComparisonTypeEnum;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface ComparisonOperationBusiness
 * @since 30/10/2024
 */
public interface ComparisonOperationBusiness extends GenericBusiness<ComparisonOperationEntity> {
    ComparisonOperationEntity create(UUID user_id, UUID company_id, ComparisonTypeEnum type, String jsonPath, String variable, UUID node_id) throws BusinessException, FactoryException, ValidationException, RepositoryException, BadRequestException;
}
