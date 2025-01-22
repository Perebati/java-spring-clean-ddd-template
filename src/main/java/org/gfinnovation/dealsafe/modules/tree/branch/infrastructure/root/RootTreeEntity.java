package org.gfinnovation.dealsafe.modules.tree.branch.infrastructure.root;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe.modules.tree.branch.infrastructure.BranchEntity;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootTreeEntity
 * @since 30/10/2024
 */

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue(BranchEntity.DISCRIMINATOR_ROOT)
@Table(name = "tree_root")
public class RootTreeEntity extends BranchEntity {
}