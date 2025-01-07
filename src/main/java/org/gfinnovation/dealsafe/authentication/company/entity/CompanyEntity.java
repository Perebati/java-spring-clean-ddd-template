package org.gfinnovation.dealsafe.authentication.company.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.modules.domain.GenericEntity;
import org.gfinnovation.dealsafe._shared.utils.annotations.Default;
import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.springframework.validation.annotation.Validated;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class CompanyEntity
 * The way authentication works in DealSafe will change in the future,
 * so don't even bother trying to understand this
 * @since 30/10/2024
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
@Validated
public class CompanyEntity extends GenericEntity {
    @NotNull(message = "O nome da empresa não pode ser nulo.")
    @Size(min = 4, max = 100, message = "O nome da empresa deve ter entre 1 e 100 caracteres.")
    @JsonView(CompanyView.class)
    private String name;

    private Set<UserEntity> users = new HashSet<>();

    @Default
    public CompanyEntity(
            @NotNull @Size(min = 4) String name,
            @NotNull UserEntity user
    ) {
        this.name = name;
        this.users.add(user);
    }

    public CompanyEntity(
            @NotNull @Size(min = 4) String name,
            @NotNull Set<UserEntity> users) {
        if (users.isEmpty()) {
            throw new BusinessException("Company must have at least one user");
        }
        this.name = name;
        this.users = users;
    }

    public CompanyEntity updateEntity(CompanyEntity companyEntity) {
        this.name = companyEntity.getName();
        this.users = companyEntity.getUsers();
        return this;
    }

    public void addUser(UserEntity user) {
        this.users.add(user);
    }

    public void removeUser(UserEntity user) {
        this.users.remove(user);
    }

    //TODO
    public interface CompanyView {
    }
}
