package com.mynkjain.chatkaro.service;

import com.mynkjain.chatkaro.model.Story;
import com.mynkjain.chatkaro.model.User;
import com.mynkjain.chatkaro.repository.StoryRepository;
import com.mynkjain.chatkaro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class StoryServiceImplementation implements StoryService{

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Story createStory(Story story, User user) {
        Story createdStory = new Story();
        createdStory.setCaptions(story.getCaptions());
        createdStory.setImage(story.getImage());
        createdStory.setUser(user);
        createdStory.setTimestamp(LocalDateTime.now());

        return storyRepository.save(createdStory);
    }

    @Override
    public List<Story> findStoryByUserId(Integer userId) throws Exception {

        User user = userService.findUserById(userId);

        return storyRepository.findByUserId(userId);
    }
}