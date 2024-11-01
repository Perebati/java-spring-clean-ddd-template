package org.gfinnovation.dealsafe.domains.input.application.business.interfaces;

import org.gfinnovation.dealsafe._shared.application.GenericBusiness;
import org.gfinnovation.dealsafe.domains.input.entity.InputEntity;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class InputBusiness
 * @since 30/10/2024
 */
public interface InputBusiness extends GenericBusiness<InputEntity> {
    InputEntity create(UUID user_id, UUID company_id, String name, String json) throws RuntimeException;
}
