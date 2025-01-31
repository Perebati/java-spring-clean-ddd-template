package org.gfinnovation.dealsafe.modules.engine.inbound;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.gfinnovation.dealsafe.modules.engine.Engine;
import org.gfinnovation.dealsafe.modules.engine.inbound.interfaces.EngineController;
import org.gfinnovation.dealsafe.modules.tree.root.application.service.interfaces.RootTreeService;
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
@AllArgsConstructor
public class EngineControllerImpl implements EngineController {
    private final RootTreeService rootTreeService;
    private final Engine engine;

    @Override
    public ResponseEntity<Boolean> validateJson(UUID root_id, JsonNode jsonNode) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            mapper.findAndRegisterModules();

            String validationTreeJson = mapper.writeValueAsString(this.rootTreeService.readGenericRoot(root_id));

            JsonNode validationRoot = mapper.readTree(validationTreeJson);

            return ResponseEntity.ok(engine.bfsValidation(validationRoot, jsonNode));
        } catch (JsonProcessingException | InvocationTargetException | NoSuchMethodException | IllegalAccessException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
