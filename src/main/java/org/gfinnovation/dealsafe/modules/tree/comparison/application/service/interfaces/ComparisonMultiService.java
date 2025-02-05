package org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces;

import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonMultiRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;

public interface ComparisonMultiService extends GenericService<ComparisonMulti> {
    ComparisonMulti create(
            ComparisonMultiRecord input
    ) throws SystemGlobalException;
}
