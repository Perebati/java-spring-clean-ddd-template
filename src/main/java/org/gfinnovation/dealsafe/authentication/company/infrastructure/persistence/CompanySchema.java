package org.gfinnovation.dealsafe.authentication.company.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.GenericSchema;
import org.gfinnovation.dealsafe.authentication.user.infrastructure.persistence.UserSchema;

import java.util.Set;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class CompanySchema
 * The way authentication works in DealSafe will change in the future,
 * so don't even bother trying to understand this
 * @since 30/10/2024
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "auth_company")
public class CompanySchema extends GenericSchema {
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(cascade = {CascadeType.REMOVE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "_relation_company_x_user",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<UserSchema> users;
}
