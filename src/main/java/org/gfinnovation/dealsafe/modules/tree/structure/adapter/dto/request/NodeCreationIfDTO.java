package org.gfinnovation.dealsafe.modules.tree.structure.adapter.dto.request;

import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeIf;

import java.util.UUID;

public record NodeCreationIfDTO(String name, UUID parent_id, NodeTreeIf.SetNode position) {
}
