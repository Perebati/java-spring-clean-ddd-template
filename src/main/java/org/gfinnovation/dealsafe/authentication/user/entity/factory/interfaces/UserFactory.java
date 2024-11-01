package org.gfinnovation.dealsafe.authentication.user.entity.factory.interfaces;

import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface UserFactory
 * @since 30/10/2024
 */
public interface UserFactory {
    UserEntity createUser(String name) throws RuntimeException;
}
