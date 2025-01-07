package org.gfinnovation.dealsafe.engine.inbound;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gfinnovation.dealsafe.engine.Engine;
import org.gfinnovation.dealsafe.engine.inbound.interfaces.EngineController;
import org.gfinnovation.dealsafe.modules.tree.application.service.interfaces.TreeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class EngineControllerImpl
 * @since 04/11/2024
 */

@Controller
public class EngineControllerImpl implements EngineController {
    private final TreeService treeService;
    private final Engine engine;

    public EngineControllerImpl(TreeService treeService, Engine engine) {
        this.treeService = treeService;
        this.engine = engine;
    }

    @Override
    public ResponseEntity<Boolean> validateJson(UUID root_id, JsonNode jsonNode) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            mapper.findAndRegisterModules();

            String validationTreeJson = mapper.writeValueAsString(this.treeService.getRootTreeBusiness().readGenericRoot(root_id));

            JsonNode validationRoot = mapper.readTree(validationTreeJson);

            return ResponseEntity.ok(engine.bfsValidation(validationRoot, jsonNode));
        } catch (JsonProcessingException | InvocationTargetException | NoSuchMethodException | IllegalAccessException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
