package com.mynkjain.chatkaro.service;

import com.mynkjain.chatkaro.model.Reels;
import com.mynkjain.chatkaro.model.User;

import java.util.List;

public interface ReelsService {

    public Reels createReel(Reels reel, User user);
    public List<Reels> findAllReels();
    public List<Reels> findUsersReel(Integer userId) throws Exception;
}
