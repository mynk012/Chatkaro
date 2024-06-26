package com.mynkjain.chatkaro.service;

import com.mynkjain.chatkaro.model.Reels;
import com.mynkjain.chatkaro.model.User;
import com.mynkjain.chatkaro.repository.ReelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReelsServiceImplementation implements ReelsService {

    @Autowired
    private ReelsRepository reelsRepository;
    @Autowired
    private UserService userService;

    @Override
    public Reels createReel(Reels reel, User user) {

        Reels createReel = new Reels();
        createReel.setTitle(reel.getTitle());
        createReel.setVideo(reel.getVideo());
        createReel.setUser(user);

        return reelsRepository.save(createReel);

    }

    @Override
    public List<Reels> findAllReels() {

        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUsersReel(Integer userId) throws Exception {

        userService.findUserById(userId);
        return reelsRepository.findByUserId(userId);

    }
}
