package org.gfinnovation.dealsafe.authentication.user.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.entity.GenericEntity;
import org.gfinnovation.dealsafe.utils.annotations.Default;
import org.springframework.validation.annotation.Validated;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class UserEntity
 * The way authentication works in DealSafe will change in the future,
 * so don't even bother trying to understand this
 * @since 30/10/2024
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
@Validated
public class UserEntity extends GenericEntity {
    @NotNull(message = "O nome da não pode ser nulo.")
    @Size(min = 4, max = 100, message = "O nome da pessoa deve ter entre 4 e 100 caracteres.")
    @JsonView(UserEntity.UserView.class)
    private String name;

    @Default
    public UserEntity(
            @NotNull @Size(min = 4) String name) {
        this.name = name;
    }

    //TODO
    public interface UserView {
    }
}