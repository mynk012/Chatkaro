package com.mynkjain.chatkaro.service;

import com.mynkjain.chatkaro.model.Chat;
import com.mynkjain.chatkaro.model.Message;
import com.mynkjain.chatkaro.model.User;
import com.mynkjain.chatkaro.repository.ChatRepository;
import com.mynkjain.chatkaro.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImplementation implements MessageService{

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Message createMessage(User user, Integer chatId, Message req) throws Exception {

        Message message = new Message();
        Chat chat = chatService.findChatById(chatId);

        message.setChat(chat);
        message.setContent(req.getContent());
        message.setImage(req.getImage());
        message.setUser(user);
        message.setTimetamp(LocalDateTime.now());
        Message savedMessages = messageRepository.save(message);

        chat.getMessages().add(savedMessages);
        chatRepository.save(chat);
        return savedMessages;
    }

    @Override
    public List<Message> findChatsMessages(Integer chatId) throws Exception {

        Chat chat = chatService.findChatById(chatId);
        return messageRepository.findByChatId(chatId);
    }
}
