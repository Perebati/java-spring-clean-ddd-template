package org.gfinnovation.dealsafe.authentication.user.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.entity.GenericEntity;
import org.gfinnovation.dealsafe.utils.annotations.Default;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

/**
 * The way authentication works in DealSafe will change in the future,
 * so don't even bother trying to understand this.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class UserEntity
 * @since 30/10/2024
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
@Validated
public class UserEntity extends GenericEntity {
    private String email;
    private UUID companyId;

    @Default
    public UserEntity(
            @NotNull @Size(min = 4) String email) {
        this.email = email;
    }
}