package mcp.config

import mcp.tools.PalragoService
import org.springframework.ai.tool.ToolCallbackProvider
import org.springframework.ai.tool.method.MethodToolCallbackProvider
import org.springframework.context.annotation.Configuration

@Configuration
class MCP {




    fun palragoTools(palragoService: PalragoService): ToolCallbackProvider {
        return MethodToolCallbackProvider.builder().toolObjects(palragoService).build();
    }
}