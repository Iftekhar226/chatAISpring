package com.answer.demo.answer.rest;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


import java.util.Map;

@RestController
public class AiChatSupportController {

    @Autowired
    private  OllamaChatModel chatModel;


//    public AiChatSupportController(OllamaChatModel  chatModel) {
//        this.chatModel = chatModel;
//    }

    @GetMapping("/ai/generate")
    public Map<String,String> generate(@RequestParam("message") String message) {

        chatModel.call(message);
        return Map.of("generation", chatModel.call(message));
    }
    @GetMapping("/ai")
    public Map<String,String> generateAI() {
        return Map.of("generation", "hiiiiiiiiiiiiii ");
    }

    @GetMapping("/ai/generateStream")
    public Flux<String> generateStream(@RequestParam("message") String message) {
//        Prompt prompt = new Prompt(new UserMessage(message));
        return chatModel.stream(message);
    }

    @GetMapping("/ai/generateStreamChat")
    public Flux<ChatResponse> generateStreamChat(@RequestParam("message") String message) {
        Prompt prompt = new Prompt(new UserMessage(message));
        return chatModel.stream(prompt);
    }
}
