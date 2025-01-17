package org.gfinnovation.dealsafe.modules.tree.action.infrastructure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.GenericBusinessSchema;

/**
 * This class will soon be replaced in later versions of dealsafe.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class OperationActionSchema
 * @since 30/10/2024
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "tree_root_node_operation_action")
public class ActionOperationEntity extends GenericBusinessSchema {

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String message;
}
