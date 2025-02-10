package org.gfinnovation.dealsafe.modules.tree.action.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.domain.GenericBusinessClass;

/**
 * An action will 'do' something when it's called.
 * This one only sends a message to a URL.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class ActionOperation
 * @since v1.0 (30/11/2024)
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class ActionOperation extends GenericBusinessClass {
    private String url;
    private String message;

    public ActionOperation(String url, String message) {
        this.url = url;
        this.message = message;
    }
}