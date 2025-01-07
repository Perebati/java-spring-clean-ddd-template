package org.gfinnovation.dealsafe.modules.tree.application.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.gfinnovation.dealsafe.modules.tree.application.service.components.interfaces.NodeTreeService;
import org.gfinnovation.dealsafe.modules.tree.application.service.components.interfaces.RootTreeService;
import org.gfinnovation.dealsafe.modules.tree.application.service.interfaces.TreeService;
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
class TreeServiceImpl implements TreeService {
    private final NodeTreeService nodeTreeBusiness;
    private final RootTreeService rootTreeBusiness;
}