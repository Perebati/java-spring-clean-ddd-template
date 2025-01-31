package org.gfinnovation.dealsafe.modules.tree.root.application.service.interfaces;

import jakarta.validation.ValidationException;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.input.domain.valueobjects.predefined.enums.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeStatic;


public interface RootTreeStaticService extends GenericService<RootTreeStatic> {
    RootTreeStatic create(
            String name,
            PredefinedTypeEnum static_input
    ) throws ServiceException, ValidationException, BadRequestException;
}
