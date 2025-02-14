package org.gfinnovation.dealsafe.engine.generator.adapter.web;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.gfinnovation.dealsafe.engine.generator.adapter.web.interfaces.TreeGeneratorController;
import org.gfinnovation.dealsafe.engine.generator.application.usecase.CreateTreeUseCase;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.AdapterException;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.RootTree;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class TreeController
 * @since v1.0 (12/02/2025)
 */
@RequiredArgsConstructor
@Controller
public class TreeGeneratorControllerImpl implements TreeGeneratorController {

    private final CreateTreeUseCase createTreeUseCase;

    public ResponseEntity<RootTree<?>> createTree(@NotNull RootTree<?> request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.createTreeUseCase.execute(
                            request
                    ));
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new AdapterException("Unexpected error in operation creation.");
        }
    }
}