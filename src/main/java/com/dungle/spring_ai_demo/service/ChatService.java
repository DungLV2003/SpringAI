package com.dungle.spring_ai_demo.service;

import com.dungle.spring_ai_demo.dto.BillItem;
import com.dungle.spring_ai_demo.dto.ChatRequest;
import com.dungle.spring_ai_demo.dto.ExpenseInfo;
import com.dungle.spring_ai_demo.dto.FilmInfo;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ChatService {
    private final ChatClient chatClient;

    public ChatService(ChatClient.Builder builder) {
        chatClient = builder.build();
    }
//    public String chat(ChatRequest request) {
//        SystemMessage systemMessage = new SystemMessage("""
//                You are DungLe.AI
//                 You should response with a super funny voice""");
//        UserMessage userMessage = new UserMessage(request.message());
//        Prompt prompt = new Prompt(systemMessage, userMessage);
//
//        return chatClient.prompt(prompt)
//               .call()
//               .content();
//    }

//    public List<FilmInfo> chat(ChatRequest request) {
//        SystemMessage systemMessage = new SystemMessage("""
//                You are DungLe.AI
//                 You should response with a super funny voice""");
//        UserMessage userMessage = new UserMessage(request.message());
//        Prompt prompt = new Prompt(systemMessage, userMessage);
//
//        return chatClient.prompt(prompt)
//                .call()
//                .entity(new ParameterizedTypeReference<List<FilmInfo>>() {
//                });
//    }

    public ExpenseInfo chat(ChatRequest request) {
        SystemMessage systemMessage = new SystemMessage("""
                You are DungLe.AI
                 You should response with a super funny voice""");
        UserMessage userMessage = new UserMessage(request.message());
        Prompt prompt = new Prompt(systemMessage, userMessage);

        return chatClient.prompt(prompt)
                .call()
                .entity(ExpenseInfo.class);
    }

//    public String chatWithImage(MultipartFile file, String message) {
//        Media media = Media.builder()
//                .data(file.getResource())
//                .mimeType(MimeTypeUtils.parseMimeType(file.getContentType()))
//                .build();
//
//        ChatOptions chatOptions = ChatOptions.builder()
//                .temperature(0.1) // do chinh xac
//                .build();
//
//        return chatClient.prompt()
//                .options(chatOptions)
//                .system("You are DungLe.AI")
//                .user(promptUserSpec
//                -> promptUserSpec.media(media)
//                .text(message))
//                .call()
//                .content();
//
//    }

    public List<BillItem> chatWithImage(MultipartFile file, String message) {
        Media media = Media.builder()
                .data(file.getResource())
                .mimeType(MimeTypeUtils.parseMimeType(file.getContentType()))
                .build();

        ChatOptions chatOptions = ChatOptions.builder()
                .temperature(0.1) // do chinh xac
                .build();

        return chatClient.prompt()
                .options(chatOptions)
                .system("You are DungLe.AI")
                .user(promptUserSpec
                        -> promptUserSpec.media(media)
                        .text(message))
                .call()
                .entity(new ParameterizedTypeReference<List<BillItem>>() {
                });

    }
}
