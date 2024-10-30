package org.gfinnovation.dealsafe.repository;


import org.gfinnovation.dealsafe._shared.GenericRepositoryTest;
import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;
import org.gfinnovation.dealsafe.authentication.user.entity.factory.interfaces.UserFactory;
import org.gfinnovation.dealsafe.authentication.user.entity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class UserRepositoryTest extends GenericRepositoryTest<UserEntity> {

    @Autowired
    private UserFactory userFactory;

    @Autowired
    private UserRepository userRepository;


    @Override
    protected org.gfinnovation.dealsafe.authentication.user.entity.UserEntity createEntity() {
        return this.userFactory.createUser("Taba júnior");
    }

    @Override
    protected org.gfinnovation.dealsafe.authentication.user.entity.repository.UserRepository createRepository() {
        return this.userRepository;
    }
}
