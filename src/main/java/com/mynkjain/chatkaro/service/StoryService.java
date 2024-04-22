package com.mynkjain.chatkaro.service;

import com.mynkjain.chatkaro.model.Story;
import com.mynkjain.chatkaro.model.User;

import java.util.List;

public interface StoryService {

    public Story createStory(Story story, User user);

    public List<Story> findStoryByUserId(Integer userId) throws Exception;
}