package org.gfinnovation.dealsafe.modules.input.adapter.web.request;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.lang.NonNull;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class CreateInputRecord
 * @since v1.0 (07/02/2025)
 */
public record CreateInputData(
        @NonNull String name,
        @NonNull JsonNode json) {};
