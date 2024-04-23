package com.mynkjain.chatkaro.service;

import com.mynkjain.chatkaro.model.Chat;
import com.mynkjain.chatkaro.model.User;
import com.mynkjain.chatkaro.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImplementation implements ChatService{


    @Autowired
    private UserService userService;

    @Autowired
    private ChatRepository chatRepository;
    @Override
    public Chat createChat(User reqUser, User user2) {
        Chat isExist = chatRepository.findChatByUserId(user2, reqUser);

        if(isExist!=null){
            return isExist;
        }

        Chat chat = new Chat();
        chat.getUsers().add(user2);
        chat.getUsers().add(reqUser);
        chat.setTimestamp(LocalDateTime.now());
        return chatRepository.save(chat);

    }

    @Override
    public Chat findChatById(Integer chatId) throws Exception {

        Optional<Chat> chat =chatRepository.findById(chatId);

        if(chat.isPresent()) {
            return chat.get();
        }
        throw new Exception("chat not dont with id - " + chatId);
    }

    @Override
    public List<Chat> findUsersChat(Integer userId) {

        List<Chat> chats=chatRepository.findByUsersId(userId);

        return chats;
    }
}
