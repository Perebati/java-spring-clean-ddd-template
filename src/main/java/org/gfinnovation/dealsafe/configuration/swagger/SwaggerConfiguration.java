package org.gfinnovation.dealsafe.configuration.swagger;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class SwaggerConfiguration
 * @since v1.0 (30/11/2024)
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Dealsafe API", version = "v1"),
        tags = {
                @Tag(name = "Dealboard", description = "Rotas de integração com o Dealboard."),
                @Tag(name = "Input", description = "Operações relacionadas a criação de inputs para árvores de validação."),
                @Tag(name = "Árvore - Geral", description = "Operações que são válidas tanto para Nó quanto Raíz."),
                @Tag(name = "Árvore - Raiz", description = "Operações de CRUD sobre a Raiz da árvore de validação."),
                @Tag(name = "Árvore - Nó", description = "Operações de CRUD sobre os Nós da árvore de validação."),
                @Tag(name = "Operação", description = "Operação sobre a criação de condicionais na árvore de validação."),
                @Tag(name = "Engine", description = "Operação de validação do json através da árvore de validação."),
                @Tag(name = "Teste", description = "Teste")
        }
)
@SecuritySchemes({
        @SecurityScheme(
                name = "BearerAuth",
                type = SecuritySchemeType.HTTP,
                scheme = "bearer",
                bearerFormat = "JWT"
        )
})
public class SwaggerConfiguration {
}