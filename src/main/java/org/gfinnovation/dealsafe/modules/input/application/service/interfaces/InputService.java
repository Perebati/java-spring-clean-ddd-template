package org.gfinnovation.dealsafe.modules.input.application.service.interfaces;

import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.input.domain.Input;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class InputBusiness
 * @since 30/10/2024
 */

public interface InputService extends GenericService<Input> {
    Input create(String name, String json) throws SystemGlobalException;
}
