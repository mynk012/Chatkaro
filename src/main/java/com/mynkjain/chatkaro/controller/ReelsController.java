package com.mynkjain.chatkaro.controller;


import com.mynkjain.chatkaro.model.Reels;
import com.mynkjain.chatkaro.model.User;
import com.mynkjain.chatkaro.service.ReelsService;
import com.mynkjain.chatkaro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reels")
public class ReelsController {

    @Autowired
    private ReelsService reelsService;

    @Autowired
    private UserService userService;

    @PostMapping
    public Reels createReels(@RequestHeader("Authorization") String jwt, @RequestBody Reels reel){

        User reqUser = userService.findUserByJwt(jwt);
        Reels createReels = reelsService.createReel(reel, reqUser);
        return createReels;
    }

    @GetMapping
    public List<Reels> getAllReels(){

        List<Reels>  reels = reelsService.findAllReels();
        return reels;
    }

    @GetMapping("/user/{userId}")
    public List<Reels> getUserReels(@PathVariable Integer userId){

        List<Reels>  reels = reelsService.findAllReels();
        return reels;
    }

}
