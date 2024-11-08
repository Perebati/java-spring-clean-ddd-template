package org.gfinnovation.dealsafe.authentication.user.infrastructure.persistence.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.gfinnovation.dealsafe._shared.infrastructure.persistence.repository.GenericRepositoryImpl;
import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;
import org.gfinnovation.dealsafe.authentication.user.entity.repository.UserRepository;
import org.gfinnovation.dealsafe.authentication.user.infrastructure.persistence.UserSchema;
import org.gfinnovation.dealsafe.authentication.user.infrastructure.persistence.mapper.UserMapper;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
    private final EntityManager entityManager;
    private final UserMapper userMapper;

    UserRepositoryImpl(EntityManager entityManager, UserMapper userMapper) {
        super(userMapper, new SimpleJpaRepository<>(UserSchema.class, entityManager));
        this.entityManager = entityManager;
        this.userMapper = userMapper;
    }

    public Optional<UserEntity> findUserByEmail(String email) {
        String query = "SELECT u FROM UserSchema u WHERE u.email = :email";
        try {
            UserSchema result = entityManager.createQuery(query, UserSchema.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return Optional.of(userMapper.toEntity(result));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}