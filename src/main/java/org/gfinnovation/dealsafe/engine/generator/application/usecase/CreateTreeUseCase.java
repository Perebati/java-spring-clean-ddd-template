package org.gfinnovation.dealsafe.engine.generator.application.usecase;

import org.gfinnovation.dealsafe._shared.application.usecase.UseCase;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;
import org.gfinnovation.dealsafe.engine.generator.application.service.interfaces.TreeGeneratorService;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.RootTree;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class CreateTreeUseCase
 * @since v1.0 (13/02/2025)
 */
@Component
public class CreateTreeUseCase extends UseCase<RootTree<?>, RootTree<?>, TreeGeneratorService> {
    public CreateTreeUseCase(TreeGeneratorService treeGeneratorService) {
        super(treeGeneratorService);
    }

    @Override
    public RootTree<?> execute(RootTree<?> input) throws ApplicationException {
        return this.service.createTree(input);
    }
}
