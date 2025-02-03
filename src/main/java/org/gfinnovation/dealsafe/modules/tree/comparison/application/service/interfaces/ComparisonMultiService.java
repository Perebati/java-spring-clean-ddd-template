package org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;

import java.util.List;
import java.util.UUID;

public interface ComparisonMultiService extends GenericService<ComparisonMulti> {
    ComparisonMulti create(
            ComparisonMulti.ComparisonMultiTypeEnum type,
            String jsonPath,
            List<String> variables,
            UUID node_id
    ) throws ServiceException, BadRequestException;
}
