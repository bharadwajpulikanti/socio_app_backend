package com.media.service;

import java.util.List;

import com.media.models.Story;
import com.media.models.User;

public interface StoryService {

	public Story createStory(Story story, User user);

	public List<Story> findStoryByUserId(Integer userId) throws Exception;

}
