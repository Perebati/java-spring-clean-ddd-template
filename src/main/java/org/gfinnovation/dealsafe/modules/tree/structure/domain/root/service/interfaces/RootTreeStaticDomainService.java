package org.gfinnovation.dealsafe.modules.tree.structure.domain.root.service.interfaces;

import jakarta.validation.ValidationException;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.input.domain.valueobjects.predefined.enums.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.RootTreeStatic;


public interface RootTreeStaticDomainService extends GenericService<RootTreeStatic> {
    RootTreeStatic create(
            String name,
            PredefinedTypeEnum static_input
    ) throws ServiceException, ValidationException, BadRequestException;
}
