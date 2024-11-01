package org.gfinnovation.dealsafe.authentication.user.entity.factory;

import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;
import org.gfinnovation.dealsafe.authentication.user.entity.factory.interfaces.UserFactory;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class UserFactoryImpl
 * The way authentication works in DealSafe will change in the future,
 * so don't even bother trying to understand this
 * @since 30/10/2024
 */
@Component
class UserFactoryImpl implements UserFactory {
    public UserEntity createUser(String name) {
        return new UserEntity(name);
    }
}