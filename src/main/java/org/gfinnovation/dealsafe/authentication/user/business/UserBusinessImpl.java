package org.gfinnovation.dealsafe.authentication.user.business;

import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;
import org.gfinnovation.dealsafe.authentication.user.entity.factory.interfaces.UserFactory;
import org.gfinnovation.dealsafe.authentication.user.entity.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class UserBusinessImpl
 * The way authentication works in DealSafe will change in the future,
 * so don't even bother trying to understand this
 * @since 30/10/2024
 */
@Service
class UserBusinessImpl implements UserBusiness {
    private final UserRepository userRepository;
    private final UserFactory userFactory;

    public UserBusinessImpl(
            UserRepository userRepository,
            UserFactory userFactory
    ) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    @Override
    public UserEntity create(String email) {
        return this.userRepository.create(userFactory.createUser(email));
    }

    @Override
    public Optional<UserEntity> read(UUID id) {
        return userRepository.read(id);
    }

    @Override
    public Optional<List<UserEntity>> readAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<List<UserEntity>> readAllByIds(List<UUID> users_id) {
        return userRepository.findAllByIds(users_id);
    }

    @Override
    public Optional<UserEntity> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void check(UUID id) {
        userRepository.check(id);
    }

    @Override
    public void checkAll(Set<UUID> ids) {
        userRepository.checkAll(ids);
    }

    @Override
    public UserEntity update(UserEntity entity) {
        return userRepository.update(entity);
    }

    @Override
    public void delete(UUID id) {
        userRepository.delete(id);
    }
}
