package org.gfinnovation.dealsafe._shared.infrastructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Trivial class used for storing basic information from saved entities.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class GenericSchema
 * @since 30/10/2024
 */

@Data
@MappedSuperclass
@NoArgsConstructor
@EntityListeners(GenericSchema.EntityListener.class)
public abstract class GenericSchema {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Setter
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Setter
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Setter
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Setter
    @Column(nullable = false)
    private Boolean deleted = false;

    /**
     * Entity Listener para automatizar a configuração de campos.
     */
    public static class EntityListener {

        @PrePersist
        public void prePersist(GenericSchema schema) {
            LocalDateTime now = LocalDateTime.now();
            schema.setCreatedAt(now);
            schema.setUpdatedAt(now);
            schema.setDeleted(false);
        }

        @PreUpdate
        public void preUpdate(GenericSchema schema) {
            schema.setUpdatedAt(LocalDateTime.now());
        }

        @PreRemove
        public void preRemove(GenericSchema entity) {
            entity.setDeleted(true);
            entity.setDeletedAt(LocalDateTime.now());
        }
    }
}