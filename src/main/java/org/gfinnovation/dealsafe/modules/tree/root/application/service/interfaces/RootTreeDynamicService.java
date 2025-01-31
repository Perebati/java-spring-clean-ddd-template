package org.gfinnovation.dealsafe.modules.tree.root.application.service.interfaces;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeDynamic;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface RootTreeDynamicService extends GenericService<RootTreeDynamic> {
    CompletableFuture<RootTreeDynamic> create(
            String name,
            UUID dynamic_input
    ) throws ServiceException, ValidationException;
}
