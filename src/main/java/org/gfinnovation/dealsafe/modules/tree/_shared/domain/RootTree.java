package org.gfinnovation.dealsafe.modules.tree._shared.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonCustomList;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeStatic;
import org.gfinnovation.dealsafe.utils.annotations.Default;

import java.util.*;

/**
 * It sets off what type of tree will be build using nodes.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class RootTree
 * @since v1.0 (30/11/2024)
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.DEDUCTION
)
@JsonSubTypes({
        @JsonSubTypes.Type(RootTreeStatic.class),
        @JsonSubTypes.Type(RootTreeDynamic.class)
})
public class RootTree<T extends NodeInput> extends Node<T> {
    private String name;

    @JsonIgnore
    private List<RootTreeHistory> history = new ArrayList<>();

    @ArraySchema(schema = @Schema(
            oneOf = {
                    NodeTreeBlock.class,
                    NodeTreeIf.class,
                    ComparisonSingular.class,
                    ComparisonMulti.class,
                    ComparisonCustomList.class
            }
    ))
    private List<NodeTree<T>> nodes = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String version;

    @Default
    public RootTree(
            String name,
            NodeType nodeType
    ) {
        this.name = name;
        setNodeType(nodeType);
        this.version = this.versionManager(name);
    }

    public RootTree(
            String name,
            NodeType nodeType,
            List<NodeTree<T>> nodes
    ) {
        this.name = name;
        setNodeType(nodeType);
        this.nodes = nodes;
        this.version = this.versionManager(name);
    }

    public void updateData(String name, String version) {
        this.name = name;
        if (Objects.isNull(version)) {
            this.version = this.versionManager(name);
        } else {
            this.version = version;
        }
    }

    public void addNode(NodeTree<T> node) {
        this.nodes.add(node);
        node.setParent(this);
    }

    public void removeNode(UUID nodeId) {
        Optional<NodeTree<T>> nodeOpt = nodes.stream()
                .filter(n -> n.getId().equals(nodeId))
                .findFirst();
        nodeOpt.ifPresent(node -> {
            nodes.remove(node);
            node.setParent(null);
        });
    }

    public Optional<RootTreeHistory> findHistoryById(UUID id) {
        return history.stream()
                .filter(h -> h.getId().equals(id))
                .findFirst();
    }

    private String versionManager(String name) {
        if (name == null) {
            return "1.0";
        }

        if (this.name == null || !this.name.equals(name)) {
            this.name = name;
            if (this.version == null) {
                this.version = "1.0";
            } else {
                String[] parts = this.version.split("\\.");
                int major = Integer.parseInt(parts[0]);
                major++;
                this.version = major + ".0";
            }
            return this.version;
        }

        if (this.version == null) {
            this.version = "1.0";
        } else {
            String[] parts = this.version.split("\\.");
            int major = Integer.parseInt(parts[0]);
            int minor = Integer.parseInt(parts[1]);
            minor++;
            this.version = major + "." + minor;
        }
        return this.version;
    }

    public void addHistory(RootTreeHistory history) {
        this.history.add(history);
    }
}