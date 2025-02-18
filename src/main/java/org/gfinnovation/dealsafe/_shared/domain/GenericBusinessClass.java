package org.gfinnovation.dealsafe._shared.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

/**
 * All classes apart from those that are linked
 * to authentication should extend from GenericBusinessClass.
 * NOTE THIS: The application and entity layer of each domain DOESN'T ACCESS
 * GenericBusinessEntity, which is a JPA class equivalent to this one.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class GenericBusinessClass
 * @since v1.0 (30/11/2024)
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class GenericBusinessClass extends GenericClass {
    @JsonIgnore
    private UUID userId;

    @JsonIgnore
    private UUID whitelabelId;
}