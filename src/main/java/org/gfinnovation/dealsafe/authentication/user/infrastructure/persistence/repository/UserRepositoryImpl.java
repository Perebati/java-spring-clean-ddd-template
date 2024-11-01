package org.gfinnovation.dealsafe.authentication.user.infrastructure.persistence.repository;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.infrastructure.GenericRepositoryImpl;
import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;
import org.gfinnovation.dealsafe.authentication.user.entity.repository.UserRepository;
import org.gfinnovation.dealsafe.authentication.user.infrastructure.persistence.UserSchema;
import org.gfinnovation.dealsafe.authentication.user.infrastructure.persistence.mapper.UserMapper;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class UserRepositoryImpl
 * @since 30/10/2024
 */
@Repository
class UserRepositoryImpl
        extends GenericRepositoryImpl<UserEntity, UserSchema>
        implements UserRepository {

    UserRepositoryImpl(UserMapper mapper, EntityManager entityManager) {
        super(mapper, new SimpleJpaRepository<>(UserSchema.class, entityManager));
    }
}