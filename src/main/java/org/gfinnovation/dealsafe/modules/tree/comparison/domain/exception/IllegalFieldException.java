package org.gfinnovation.dealsafe.modules.tree.comparison.domain.exception;

import org.gfinnovation.dealsafe.exception.SystemGlobalException;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class IllegalArgumentException
 * @since v1.0 (14/02/2025)
 */

public class IllegalFieldException extends SystemGlobalException {
    public IllegalFieldException(String message, Throwable cause) {
        super(message, cause);
    }
}
