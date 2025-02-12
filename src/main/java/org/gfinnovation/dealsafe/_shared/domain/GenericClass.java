package org.gfinnovation.dealsafe._shared.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime deletedAt;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean deleted;
}