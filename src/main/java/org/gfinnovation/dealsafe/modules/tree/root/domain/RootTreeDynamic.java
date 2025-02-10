package org.gfinnovation.dealsafe.modules.tree.root.domain;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe.utils.annotations.Default;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTree;

import java.util.LinkedList;
import java.util.UUID;

/**
 * Maps Root attributes if the input of the validation tree is dynamic.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class RootTreeDynamic
 * @since v1.0 (30/11/2024)
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RootTreeDynamic extends RootTree<JsonNode> {
    private UUID dynamicInputId;

    @Default
    public RootTreeDynamic(
            String name,
            UUID dynamicInputId
    ) {
        super(name, NodeType.ROOT_DYNAMIC);
        this.dynamicInputId = dynamicInputId;
    }

    public RootTreeDynamic(
            String name,
            UUID dynamicInputId,
            LinkedList<NodeTree<JsonNode>> nodes
    ) {
        super(name, NodeType.ROOT_STATIC, nodes);
        this.dynamicInputId = dynamicInputId;
    }

    @Override
    public boolean traverse(JsonNode inputData) {
        for (NodeTree<JsonNode> node : getNodes()) {
            if (!node.traverse(inputData)) return false;
        }
        return true;
    }
}