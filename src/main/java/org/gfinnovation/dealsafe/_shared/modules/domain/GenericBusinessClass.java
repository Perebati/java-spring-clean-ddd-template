package org.gfinnovation.dealsafe._shared.modules.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * All classes apart from those that are linked
 * to authentication should extend from GenericBusinessClass.
 * NOTE THIS: The application and entity layer of each domain DOESN'T ACCESS
 * GenericBusinessEntity, which is a JPA class equivalent to this one.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class GenericBusinessClass
 * @since 30/10/2024
 */

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public abstract class GenericBusinessClass extends GenericClass {
    private UUID user_id;
    private UUID company_id;
}