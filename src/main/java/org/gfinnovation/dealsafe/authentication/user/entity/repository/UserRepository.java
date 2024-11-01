package org.gfinnovation.dealsafe.authentication.user.entity.repository;

import org.gfinnovation.dealsafe._shared.entity.GenericRepository;
import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;

import java.util.Optional;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface UserRepository
 * @since 30/10/2024
 */
public interface UserRepository extends GenericRepository<UserEntity> {
    Optional<UserEntity> findUserByEmail(String email);
}
