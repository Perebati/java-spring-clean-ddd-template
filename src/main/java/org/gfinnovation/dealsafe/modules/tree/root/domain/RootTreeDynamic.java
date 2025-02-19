package org.gfinnovation.dealsafe.modules.tree.root.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.NodeInput;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.NodeTree;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.RootTree;
import org.gfinnovation.dealsafe.utils.annotations.Default;

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
@JsonTypeName("RootTreeDynamic")
@Schema(name = "RootTreeDynamic", description = "Representa um nó raiz onde o input é dinâmico na árvore de nós")
public class RootTreeDynamic extends RootTree<NodeInput> {
    private final UUID dynamicInputId;

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
            LinkedList<NodeTree<NodeInput>> nodes
    ) {
        super(name, NodeType.ROOT_STATIC, nodes);
        this.dynamicInputId = dynamicInputId;
    }

    @Override
    public boolean traverse(NodeInput inputData) {
        for (NodeTree<NodeInput> node : getNodes()) {
            if (!node.traverse(inputData)) return false;
        }
        return true;
    }
}