package org.gfinnovation.dealsafe.modules.tree.node.domain;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeTreeInterface
 * @since 21/01/2025
 */
public interface NodeTraversal<T> {
    boolean traverse(T inputData);
}
