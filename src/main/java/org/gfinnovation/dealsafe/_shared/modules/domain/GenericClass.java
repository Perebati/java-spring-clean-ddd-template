package org.gfinnovation.dealsafe._shared.modules.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Every entity in this system that is persistent should extend from GenericClass.
 * NOTE THIS: The application and entity layer of each domain DOESN'T ACCESS
 * GenericEntity, which is a JPA class equivalent to this one.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class GenericClass
 * @since v1.0 (30/11/2024)
 */
@Data
public abstract class GenericClass {
    private UUID id;

    @JsonIgnore
    private LocalDateTime createdAt;

    @JsonIgnore
    private LocalDateTime updatedAt;

    @JsonIgnore
    private LocalDateTime deletedAt;

    @JsonIgnore
    private boolean deleted;
}