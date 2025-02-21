package org.gfinnovation.dealsafe.modules.tree.root.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe.modules.input.domain.predefined.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.NodeInput;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.NodeTree;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.RootTree;
import org.gfinnovation.dealsafe.utils.annotations.Default;

import java.util.List;

/**
 * Maps Root attributes if the input of the validation tree is predefined.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class RootTreeStatic
 * @since v1.0 (30/11/2024)
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonTypeName("RootTreeStatic")
@Schema(name = "RootTreeStatic", description = "Representa um nó raiz onde o input é pré-definido na árvore de nós")
public class RootTreeStatic extends RootTree<NodeInput> {
    private PredefinedTypeEnum input_type;

    @Default
    public RootTreeStatic(
            String name,
            PredefinedTypeEnum type
    ) {
        super(name, NodeType.ROOT_STATIC);
        this.input_type = type;
    }

    @JsonCreator
    public RootTreeStatic(
            @JsonProperty("name") String name,
            @JsonProperty("input_type") PredefinedTypeEnum type,
            @JsonProperty("nodes") List<NodeTree<NodeInput>> nodes
    ) {
        super(name, NodeType.ROOT_STATIC, nodes);
        this.input_type = type;
    }

    @Override
    public boolean traverse(NodeInput inputData) {
        for (NodeTree<NodeInput> node : getNodes()) {
            if (!node.traverse(inputData)) return false;
        }
        return true;
    }
}