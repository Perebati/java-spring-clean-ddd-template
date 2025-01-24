package org.gfinnovation.dealsafe._shared.modules.infrastructure;

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
 * @class GenericEntity
 * @since 30/10/2024
 */

@Data
@MappedSuperclass
@NoArgsConstructor
@EntityListeners(GenericEntity.EntityListener.class)
public abstract class GenericEntity {
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
        public void prePersist(GenericEntity schema) {
            LocalDateTime now = LocalDateTime.now();
            schema.setCreatedAt(now);
            schema.setUpdatedAt(now);
            schema.setDeleted(false);
        }

        @PreUpdate
        public void preUpdate(GenericEntity schema) {
            schema.setUpdatedAt(LocalDateTime.now());
        }

        @PreRemove
        public void preRemove(GenericEntity entity) {
            entity.setDeleted(true);
            entity.setDeletedAt(LocalDateTime.now());
        }
    }
}