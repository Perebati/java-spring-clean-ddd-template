package org.gfinnovation.dealsafe._shared.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * JPA class used for saving business information on business classes.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class GenericBusinessSchema
 * @since 30/10/2024
 */

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class GenericBusinessSchema extends GenericSchema {

    @Column(name = "user_id", updatable = false, nullable = false)
    private UUID user_id;

    @Column(name = "company_id", updatable = false, nullable = false)
    private UUID company_id;
}