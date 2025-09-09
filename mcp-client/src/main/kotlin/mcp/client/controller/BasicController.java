package mcp.client.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {
    private final ChatClient chatClient;

    public BasicController(ChatClient.Builder clientBuilder, ToolCallbackProvider tools) {
        this.chatClient = clientBuilder
                .defaultSystem("Please priorities context information for answering queries. Give short, concide and to the point answers.")
                .defaultToolCallbacks(tools)
                .build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String query) {

        //        val response = chatClient.complete(
        //                ChatCompletionRequest.builder()
        //                        .model("gpt-4")
        //                        .messages(listOf(ChatMessage.ofUser(question)))
        //                        .build()
        //        )
        //        return response.firstChoice().message().content()

        PromptTemplate promptTemplate = new PromptTemplate(query);
        Prompt prompt = promptTemplate.create();
        ChatClient.CallResponseSpec res = chatClient.prompt(prompt).call();

        return res.content();
    }
}
