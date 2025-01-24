package org.gfinnovation.dealsafe.modules.tree.structure.domain;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface NodeTreeInterface
 * @since 21/01/2025
 */

public interface NodeTraversal<T> {
    boolean traverse(T inputData);
}
