package com.dungle.spring_ai_demo.controller;

import com.dungle.spring_ai_demo.dto.BillItem;
import com.dungle.spring_ai_demo.dto.ChatRequest;
import com.dungle.spring_ai_demo.dto.ExpenseInfo;
import com.dungle.spring_ai_demo.dto.FilmInfo;
import com.dungle.spring_ai_demo.service.ChatService;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ChatController {
    private final ChatService chatService;
    private final JdbcChatMemoryRepository chatMemoryRepository;

    public ChatController(ChatService chatService,
                          JdbcChatMemoryRepository chatMemoryRepository) {
        this.chatService = chatService;
        this.chatMemoryRepository = chatMemoryRepository;
    }

    @PostMapping("/chat")
    String chat(@RequestBody ChatRequest request){
        return chatService.chat(request);
    }

//    @PostMapping("/chat")
//    List<FilmInfo> chat(@RequestBody ChatRequest request){
//        return chatService.chat(request);
//    }

       @PostMapping("/chatForExpenseInfo")
       ExpenseInfo chatExpenseInfo(@RequestBody ChatRequest request){
           return chatService.chatForExpenseInfo(request);
        }


//    @PostMapping("/chat-with-image")
//    String chatWithImage(@RequestParam("file") MultipartFile file,
//                         @RequestParam("message") String message) {
//        return chatService.chatWithImage(file, message);
//    }

    @PostMapping("/chat-with-image")
    List<BillItem> chatWithImage(@RequestParam("file") MultipartFile file,
                                 @RequestParam("message") String message) {
        return chatService.chatWithImage(file, message);
    }
}
