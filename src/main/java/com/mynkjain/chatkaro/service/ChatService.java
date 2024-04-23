package com.mynkjain.chatkaro.service;

import com.mynkjain.chatkaro.model.Chat;
import com.mynkjain.chatkaro.model.User;

import java.util.List;

public interface ChatService {

    public Chat createChat(User reqUserId, User userId2);

    public Chat findChatById(Integer chatId) throws Exception;

    public List<Chat> findUsersChat(Integer userId);

}
