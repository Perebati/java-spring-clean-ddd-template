package org.gfinnovation.dealsafe.modules.input.domain.factory.interfaces;

import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.input.domain.Input;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class InputFactory
 * @since v1.0 (30/11/2024)
 */

public interface InputFactory {
    Input produce(String name, String json) throws SystemGlobalException;
}
