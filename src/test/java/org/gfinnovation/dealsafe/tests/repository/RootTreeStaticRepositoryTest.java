package org.gfinnovation.dealsafe.tests.repository;

import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeStatic;
import org.gfinnovation.dealsafe.modules.tree.root.domain.factory.interfaces.RootTreeFactory;
import org.gfinnovation.dealsafe.modules.tree.root.domain.valueobjects.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository.interfaces.RootTreeStaticRepository;
import org.gfinnovation.dealsafe.tests._shared.GenericBusinessRepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class RootTreeRepositoryTest
 * @since v1.0 (30/11/2024)
 */
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DisplayName("Repository test for RootTree Static")
public class RootTreeStaticRepositoryTest extends GenericBusinessRepositoryTest<RootTreeStatic> {
    @Autowired
    private RootTreeFactory rootTreeFactory;

    @Autowired
    private RootTreeStaticRepository rootTreeStaticRepository;

    @Override
    protected RootTreeStatic createEntity() {
        String name = "Teste";
        return this.rootTreeFactory.produce(name, PredefinedTypeEnum.TESTE);
    }

    @Override
    protected RootTreeStaticRepository createRepository() {
        return this.rootTreeStaticRepository;
    }
}
