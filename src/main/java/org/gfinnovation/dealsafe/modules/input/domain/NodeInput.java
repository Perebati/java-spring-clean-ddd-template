package org.gfinnovation.dealsafe.modules.input.domain;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeInput
 * @since v1.0 (10/02/2025)
 */
@AllArgsConstructor
@Getter
public class NodeInput {
    private JsonNode jsonNode;
}
