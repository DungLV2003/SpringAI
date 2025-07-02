package com.dungle.spring_ai_demo.controller;

import com.dungle.spring_ai_demo.dto.ChatRequest;
import com.dungle.spring_ai_demo.service.ChatService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    String chat(@RequestBody ChatRequest request){
        return chatService.chat(request);
    }
}
