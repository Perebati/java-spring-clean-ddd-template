package org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request;

import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;

import java.util.UUID;

public record NodeCreationIfDTO(String name,
                                UUID parent_id,
                                NodeTreeIf.SetNode position) {}