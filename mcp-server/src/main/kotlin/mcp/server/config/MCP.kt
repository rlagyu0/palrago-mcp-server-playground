package mcp.server.config

import mcp.server.tools.PalragoService
import org.springframework.ai.tool.ToolCallbackProvider
import org.springframework.ai.tool.method.MethodToolCallbackProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MCP {

    @Bean
    fun palragoTools(palragoService: PalragoService): ToolCallbackProvider {
        return MethodToolCallbackProvider.builder().toolObjects(palragoService).build();
    }
}