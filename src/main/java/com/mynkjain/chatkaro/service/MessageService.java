package com.mynkjain.chatkaro.service;

import com.mynkjain.chatkaro.model.Chat;
import com.mynkjain.chatkaro.model.Message;
import com.mynkjain.chatkaro.model.User;

import java.util.List;

public interface MessageService {

    public Message createMessage(User user, Integer chatId, Message req) throws Exception;

    public List<Message> findChatsMessages(Integer chatId) throws Exception;

}
