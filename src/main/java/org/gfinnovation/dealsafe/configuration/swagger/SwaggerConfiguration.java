package org.gfinnovation.dealsafe.configuration.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class SwaggerConfiguration
 * @authorNote n/a
 * @since 30/10/2024
 */
@Configuration
@OpenAPIDefinition(info = @Info(
        title = "DealSafe",
        version = "Alpha 0.01",
        description = "API teste"
))
public class SwaggerConfiguration {
}