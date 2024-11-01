package org.gfinnovation.dealsafe.authentication.user.business.interfaces;

import org.gfinnovation.dealsafe._shared.application.GenericBusiness;
import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface UserBusiness
 * @since 30/10/2024
 */
public interface UserBusiness extends GenericBusiness<UserEntity> {
    UserEntity create(String name) throws RuntimeException;
}
