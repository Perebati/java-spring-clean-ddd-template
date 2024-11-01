package org.gfinnovation.dealsafe.authentication.user.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gfinnovation.dealsafe._shared.infrastructure.GenericSchema;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class UserSchema
 * @since 30/10/2024
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "auth_user")
@NoArgsConstructor
public class UserSchema extends GenericSchema {
    @Column(name = "email", nullable = false)
    private String email;
    private UUID companyId;
}
