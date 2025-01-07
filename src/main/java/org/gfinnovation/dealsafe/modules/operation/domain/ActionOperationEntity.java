package org.gfinnovation.dealsafe.modules.operation.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.modules.domain.GenericBusinessEntity;

import java.util.UUID;

/**
 * An action will 'do' something when it's called.
 * This one only sends a message to a URL.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ActionOperationEntity
 * @since 30/10/2024
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class ActionOperationEntity extends GenericBusinessEntity {
    private String url;
    private String message;

    public ActionOperationEntity(UUID user_id, UUID company_id, String url, String message) {
        super(user_id, company_id);
        this.url = url;
        this.message = message;
    }
}
