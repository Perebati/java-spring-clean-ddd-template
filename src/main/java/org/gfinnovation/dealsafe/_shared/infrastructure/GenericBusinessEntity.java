package org.gfinnovation.dealsafe._shared.infrastructure;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

/**
 * JPA class used for saving business information on business classes.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class GenericBusinessEntity
 * @since v1.0 (30/11/2024)
 */
@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class GenericBusinessEntity extends GenericEntity {
    @Column(name = "user_id", updatable = false, nullable = false)
    private UUID userId;

    @Column(name = "whiteLabel_id", updatable = false, nullable = false)
    private UUID whitelabelId;
}