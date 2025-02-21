package org.gfinnovation.dealsafe.modules.tree.root.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.NodeInput;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.NodeTree;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.RootTree;
import org.gfinnovation.dealsafe.utils.annotations.Default;

import java.util.LinkedList;
import java.util.List;
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
        super(name, NodeType.ROOT_DYNAMIC, nodes);
        this.dynamicInputId = dynamicInputId;
    }

    @JsonCreator
    public RootTreeDynamic(
            @JsonProperty("name") String name,
            @JsonProperty("dynamicInputId") UUID dynamicInputId,
            @JsonProperty("nodes") List<NodeTree<NodeInput>> nodes
    ) {
        super(name, NodeType.ROOT_DYNAMIC, nodes);
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