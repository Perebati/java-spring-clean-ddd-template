package org.gfinnovation.dealsafe._shared.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

/**
 * All classes apart from those that are linked
 * to authentication should extend from GenericBusinessEntity.
 * NOTE THIS: The application and entity layer of each domain DOESN'T ACCESS
 * GenericBusinessSchema, which is a JPA class equivalent to this one.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class GenericBusinessEntity
 * @since 30/10/2024
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class GenericBusinessEntity extends org.gfinnovation.dealsafe._shared.entity.GenericEntity {
    private UUID user_id;
    private UUID company_id;

    public GenericBusinessEntity(UUID user_id, UUID company_id) {
        this.user_id = user_id;
        this.company_id = company_id;
    }
}