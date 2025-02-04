package org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonMultiRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;

public interface ComparisonMultiService extends GenericService<ComparisonMulti> {
    ComparisonMulti create(
            ComparisonMultiRecord input
    ) throws ServiceException, BadRequestException;
}
