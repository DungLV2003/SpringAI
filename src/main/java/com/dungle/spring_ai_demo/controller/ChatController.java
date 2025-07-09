package com.dungle.spring_ai_demo.controller;

import com.dungle.spring_ai_demo.dto.BillItem;
import com.dungle.spring_ai_demo.dto.ChatRequest;
import com.dungle.spring_ai_demo.dto.ExpenseInfo;
import com.dungle.spring_ai_demo.dto.FilmInfo;
import com.dungle.spring_ai_demo.service.ChatService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

//    @PostMapping("/chat")
//    String chat(@RequestBody ChatRequest request){
//        return chatService.chat(request);
//    }
//    @PostMapping("/chat")
//    List<FilmInfo> chat(@RequestBody ChatRequest request){
//        return chatService.chat(request);
//    }

        @PostMapping("/chat")
        ExpenseInfo chat(@RequestBody ChatRequest request){
            return chatService.chat(request);
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
