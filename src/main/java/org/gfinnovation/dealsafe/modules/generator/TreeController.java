package org.gfinnovation.dealsafe.modules.generator;

import org.gfinnovation.dealsafe.modules.input.domain.NodeInput;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTree;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class TreeController
 * @since v1.0 (12/02/2025)
 */
@RestController
@RequestMapping("/tree")
public class TreeController {

    @PostMapping
    public ResponseEntity<Void> createTree(@RequestBody RootTree<NodeInput> root) {
        // Jackson já terá desserializado a árvore inteira
        // a partir do "root" (que é do tipo RootTree)
        // e todos os nós filhos nas subclasses adequadas.

        // Exemplo de uso:
        processNode(root);

        return ResponseEntity.ok().build();
    }

    private void processNode(Node<?> node) {
        // processa o nó: salvar no banco, etc.
        // se tiver filhos, itera sobre eles

        if (node instanceof RootTree<?> root) {
            for (NodeTree<?> child : root.getNodes()) {
                processNode(child);
            }
        } else if (node instanceof NodeTreeBlock block) {
            // pega nodes
            for (NodeTree<NodeInput> child : block.getNodes()) {
                processNode(child);
            }
        } else if (node instanceof ComparisonSingular cs) {
            // processa
        }
        // etc.
    }
}