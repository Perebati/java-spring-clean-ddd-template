package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.infrastructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.GenericBusinessEntity;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.NodeEntity;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue(NodeEntity.DISCRIMINATOR_NODE)
@Table(name = "dealboard_blacklist")
public class CompanyListEntity extends GenericBusinessEntity {
    @Column(name = "name")
    private String name;

    @ElementCollection
    @CollectionTable(
            name = "blacklist_cnpjs",
            joinColumns = @JoinColumn(name = "blacklist_id"),
            indexes = @Index(name = "idx_cnpj", columnList = "cnpj")
    )
    @Column(name = "cnpj")
    private List<String> cnpjs = new ArrayList<>();
}