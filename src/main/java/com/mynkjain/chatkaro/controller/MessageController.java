package com.mynkjain.chatkaro.controller;

import com.mynkjain.chatkaro.model.Message;
import com.mynkjain.chatkaro.model.User;
import com.mynkjain.chatkaro.service.MessageService;
import com.mynkjain.chatkaro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @PostMapping("/chat/{chatId}")
    public Message createMessage(@RequestBody Message req,
                                 @RequestHeader("Authorization") String jwt,
                                 @PathVariable Integer chatId) throws Exception {

        User user = userService.findUserByJwt(jwt);

        Message message = messageService.createMessage(user, chatId, req);

        return message;
    }

    @GetMapping("/chat/{chatId}")
    public List<Message> findChatsMessage(@RequestHeader("Authorization") String jwt, @PathVariable Integer chatId) throws Exception {

        User user = userService.findUserByJwt(jwt);

        List<Message> messages = messageService.findChatsMessages(chatId);

        return messages;
    }
}
