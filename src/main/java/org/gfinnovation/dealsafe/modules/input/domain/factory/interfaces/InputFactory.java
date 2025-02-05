package org.gfinnovation.dealsafe.modules.input.domain.factory.interfaces;

import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.input.domain.Input;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface InputFactory
 * @since 30/10/2024
 */

public interface InputFactory {
    Input produce(String name, String json) throws SystemGlobalException;
}
