package org.gfinnovation.dealsafe.modules.tree._shared.infrastructure;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe._shared.infrastructure.GenericEntity;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class RootTreeHistoryEntity
 * @since v1.0 (19/02/2025)
 */

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue(NodeEntity.DISCRIMINATOR_ROOT)
@Table(
        name = "tree_root_history"
)
public class RootTreeHistoryEntity extends GenericEntity {
    @Column(name = "json", nullable = false, columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private String json;

    @Column(nullable = false, name = "name")
    private String name;
}
