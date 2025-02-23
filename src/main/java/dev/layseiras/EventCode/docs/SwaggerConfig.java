package dev.layseiras.EventCode.docs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "EventCode API",
                version = "1.0",
                description = "Documentação da API para gerenciamento de eventos e inscrições. " +
                        "Inclui endpoints para criação, indicação e consulta de eventos."
        )
)
public class SwaggerConfig {
}