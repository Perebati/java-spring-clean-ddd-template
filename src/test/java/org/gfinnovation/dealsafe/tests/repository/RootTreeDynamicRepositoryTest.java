package org.gfinnovation.dealsafe.tests.repository;

import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository.interfaces.RootTreeDynamicRepository;
import org.gfinnovation.dealsafe.tests._shared.GenericBusinessRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootTreeDynamicRepositoryTest
 * @since 30/10/2024
 */
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class RootTreeDynamicRepositoryTest extends GenericBusinessRepositoryTest<RootTreeDynamic> {
    @Autowired
    private RootTreeDynamicRepository rootTreeDynamicRepository;


    @Override
    protected RootTreeDynamic createEntity() {
        String name = "Teste";
        UUID dynamicInput = UUID.randomUUID();
        return new RootTreeDynamic(name, dynamicInput);
    }

    @Override
    protected RootTreeDynamicRepository createRepository() {
        return this.rootTreeDynamicRepository;
    }
}
