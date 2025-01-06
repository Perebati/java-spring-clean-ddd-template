package org.gfinnovation.dealsafe.domains.input.application.business.interfaces;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.domains.application.interfaces.GenericBusiness;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.gfinnovation.dealsafe.domains.input.entity.InputEntity;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class InputBusiness
 * @since 30/10/2024
 */

public interface InputBusiness extends GenericBusiness<InputEntity> {
    InputEntity create(String name, String json) throws BusinessException, BadRequestException;
}
