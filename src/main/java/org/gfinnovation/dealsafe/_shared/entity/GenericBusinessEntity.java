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
    private UUID userId;
    private UUID companyId;

    public GenericBusinessEntity(UUID user_id, UUID company_id) {
        this.userId = user_id;
        this.companyId = company_id;
    }
}