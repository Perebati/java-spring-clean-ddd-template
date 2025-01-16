package org.gfinnovation.dealsafe.modules.tree.operation.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.modules.domain.GenericBusinessEntity;

/**
 * An action will 'do' something when it's called.
 * This one only sends a message to a URL.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ActionOperation
 * @since 30/10/2024
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class ActionOperation extends GenericBusinessEntity {
    private String url;
    private String message;

    public ActionOperation(String url, String message) {
        this.url = url;
        this.message = message;
    }
}
