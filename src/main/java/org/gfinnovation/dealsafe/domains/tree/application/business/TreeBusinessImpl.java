package org.gfinnovation.dealsafe.domains.tree.application.business;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.gfinnovation.dealsafe.domains.tree.application.business.components.interfaces.NodeTreeBusiness;
import org.gfinnovation.dealsafe.domains.tree.application.business.components.interfaces.RootTreeBusiness;
import org.gfinnovation.dealsafe.domains.tree.application.business.interfaces.TreeBusiness;
import org.springframework.stereotype.Service;

/**
 * Main Business implementation of this domain.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class TreeBusinessImpl
 * @since 30/10/2024
 */
@Getter
@RequiredArgsConstructor
@Service
class TreeBusinessImpl implements TreeBusiness {
    private final NodeTreeBusiness nodeTreeBusiness;
    private final RootTreeBusiness rootTreeBusiness;
}
