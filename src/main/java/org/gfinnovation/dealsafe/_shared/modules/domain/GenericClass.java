package org.gfinnovation.dealsafe._shared.modules.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Every entity in this system that is persistent should extend from GenericClass.
 * NOTE THIS: The application and entity layer of each domain DOESN'T ACCESS
 * GenericEntity, which is a JPA class equivalent to this one.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class GenericClass
 * @since 30/10/2024
 */

@Data
public abstract class GenericClass {
    private UUID id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private boolean deleted;
}