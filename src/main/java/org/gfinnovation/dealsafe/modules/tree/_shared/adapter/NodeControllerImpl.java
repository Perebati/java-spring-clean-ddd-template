package org.gfinnovation.dealsafe.modules.tree._shared.adapter;

import lombok.AllArgsConstructor;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.tree._shared.application.usecase.DeleteNodeUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeControllerImpl
 * @since v1.0 (21/02/2025)
 */
@Controller
@AllArgsConstructor
public class NodeControllerImpl implements NodeController{
    private final DeleteNodeUseCase deleteNodeUseCase;

    @Override
    public ResponseEntity<Void> deleteNode(@NonNull UUID id) throws SystemGlobalException {
        this.deleteNodeUseCase.execute(id);

        return ResponseEntity.noContent().build();
    }
}
