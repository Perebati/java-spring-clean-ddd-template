package org.gfinnovation.dealsafe.engine.validator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;
import org.gfinnovation.dealsafe.modules.input.domain.NodeInput;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.root.application.service.interfaces.RootTreeDynamicService;
import org.gfinnovation.dealsafe.modules.tree.root.application.service.interfaces.RootTreeService;
import org.gfinnovation.dealsafe.modules.tree.root.application.service.interfaces.RootTreeStaticService;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTree;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class EngineControllerImpl
 * @since v1.0 (04/11/2024)
 */

@Controller
@AllArgsConstructor
public class EngineControllerImpl implements EngineController {
    private final RootTreeService rootTreeService;
    private final RootTreeStaticService rootTreeStaticService;
    private final RootTreeDynamicService rootTreeDynamicService;

    @Override
    public ResponseEntity<Boolean> validateJson(@NonNull UUID id,
                                                @NonNull JsonNode jsonNode) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            mapper.findAndRegisterModules();

            RootTree<?> rootTree = this.rootTreeService.read(id);

            if (rootTree.getNodeType().equals(Node.NodeType.ROOT_STATIC)) {
                return ResponseEntity.ok(this.rootTreeStaticService.read(id).traverse(new NodeInput(jsonNode)));
            } else {
                return ResponseEntity.ok(this.rootTreeDynamicService.read(id).traverse(new NodeInput(jsonNode)));
            }
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong while validating the json node");
        }
    }
}