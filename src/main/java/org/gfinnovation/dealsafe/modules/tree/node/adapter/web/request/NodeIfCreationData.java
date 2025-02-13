package org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request;

import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.springframework.lang.NonNull;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeIfCreationData
 * @since v1.0 (13/02/2025)
 */

public record NodeIfCreationData(@NonNull UUID parent_id, NodeTreeIf.SetNode position){}
