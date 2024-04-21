package com.mynkjain.chatkaro.controller;

import com.mynkjain.chatkaro.model.Story;
import com.mynkjain.chatkaro.model.User;
import com.mynkjain.chatkaro.service.StoryService;
import com.mynkjain.chatkaro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stories")
public class StoryController {

    @Autowired
    private StoryService storyService;

    @Autowired
    private UserService userService;


    @PostMapping("/create")
    public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt){

        User reqUser=userService.findUserByJwt(jwt);

        Story createdStory =storyService.createStory(story, reqUser);
        return createdStory;
    }


    @GetMapping("/{userId}")
    public List<Story> findAllStoryByUserIdHandler(@PathVariable Integer userId, @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser=userService.findUserByJwt(jwt);

        List<Story> stories= storyService.findStoryByUserId(userId);

        return stories;
    }

}