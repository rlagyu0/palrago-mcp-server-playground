package mcp.client.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class BasicController {
    private final ChatClient chatClient;

    public BasicController(ChatClient.Builder clientBuilder, ToolCallbackProvider tools) {
        this.chatClient = clientBuilder
                .defaultSystem("Return the value from the tool exactly as it is, without modifying or interpreting it in any way.")
                .defaultToolCallbacks(tools)
                .build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String query) {

        PromptTemplate promptTemplate = new PromptTemplate(query);
        Prompt prompt = promptTemplate.create();
        ChatClient.CallResponseSpec res = chatClient.prompt(prompt)
                .advisors(new SimpleLoggerAdvisor(
                        request -> String.format("[advisor] request : %s", request.prompt().toString()),
                        response -> String.format("[advisor] response : %s", Arrays.toString(response.getResults().toArray())),
                        0
                ))
                .call();
        return res.content();
    }
}
