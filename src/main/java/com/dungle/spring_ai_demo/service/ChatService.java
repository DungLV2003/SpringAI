package com.dungle.spring_ai_demo.service;

import com.dungle.spring_ai_demo.dto.BillItem;
import com.dungle.spring_ai_demo.dto.ChatRequest;
import com.dungle.spring_ai_demo.dto.ExpenseInfo;
import com.dungle.spring_ai_demo.dto.FilmInfo;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
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
    private final JdbcChatMemoryRepository jdbcChatMemoryRepository;

    public ChatService(ChatClient.Builder builder, JdbcChatMemoryRepository jdbcChatMemoryRepository) {
        this.jdbcChatMemoryRepository = jdbcChatMemoryRepository;

        ChatMemory chatMemory = MessageWindowChatMemory.builder()
                .chatMemoryRepository(jdbcChatMemoryRepository)
                .maxMessages(30)
                .build();

        chatClient = builder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }


    public String chat(ChatRequest request) {
        String converstationId = "conversation2";
        SystemMessage systemMessage = new SystemMessage("""
                You are DungLe.AI
                 You should response with a super funny voice""");
        UserMessage userMessage = new UserMessage(request.message());
        Prompt prompt = new Prompt(systemMessage, userMessage);

        return chatClient.prompt(prompt)
                .advisors(advisor -> advisor.param(
                        ChatMemory.CONVERSATION_ID, converstationId
                ))
               .call()
               .content();
    }

    public List<FilmInfo> chatForFileInfo(ChatRequest request) {
        SystemMessage systemMessage = new SystemMessage("""
                You are DungLe.AI
                 You should response with a super funny voice""");
        UserMessage userMessage = new UserMessage(request.message());
        Prompt prompt = new Prompt(systemMessage, userMessage);

        return chatClient.prompt(prompt)
                .call()
                .entity(new ParameterizedTypeReference<List<FilmInfo>>() {
                });
    }

    public ExpenseInfo chatForExpenseInfo(ChatRequest request) {
        String converstationId = "conversation1";
        SystemMessage systemMessage = new SystemMessage("""
                You are DungLe.AI
                You should response with a super funny voice""");
        UserMessage userMessage = new UserMessage(request.message());
        Prompt prompt = new Prompt(systemMessage, userMessage);

        return chatClient.prompt(prompt)
                .advisors(advisor -> advisor.param(
                        ChatMemory.CONVERSATION_ID, converstationId
                ))
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
