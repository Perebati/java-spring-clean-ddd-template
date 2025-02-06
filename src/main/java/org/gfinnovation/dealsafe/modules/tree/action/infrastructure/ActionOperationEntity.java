package org.gfinnovation.dealsafe.modules.tree.action.infrastructure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.GenericBusinessEntity;

/**
 * This class will soon be replaced in later versions of dealsafe.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class OperationActionSchema
 * @since v1.0 (30/11/2024)
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "tree_root_node_operation_action")
public class ActionOperationEntity extends GenericBusinessEntity {

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String message;
}
