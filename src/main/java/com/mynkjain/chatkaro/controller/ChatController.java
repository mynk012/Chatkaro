package com.mynkjain.chatkaro.controller;

import com.mynkjain.chatkaro.model.Chat;
import com.mynkjain.chatkaro.model.User;
import com.mynkjain.chatkaro.request.ChatRequest;
import com.mynkjain.chatkaro.service.ChatService;
import com.mynkjain.chatkaro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @PostMapping
    public Chat createChat(@RequestHeader("Authorization")String jwt, @RequestBody ChatRequest req) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);

        User user2 = userService.findUserById(req.getUserId());

        Chat chat =  chatService.createChat(reqUser,user2);

        return chat;
    }

    @GetMapping
    public List<Chat> findChat(@RequestHeader("Authorization")String jwt) throws Exception {

        User user = userService.findUserByJwt(jwt);

        List<Chat> chats = chatService.findUsersChat(user.getId());

        return chats;
    }
}
