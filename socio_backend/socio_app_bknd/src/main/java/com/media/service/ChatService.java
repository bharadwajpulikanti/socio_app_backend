package com.media.service;

import java.util.List;

import com.media.models.Chat;
import com.media.models.User;

public interface ChatService {

	public Chat createChat(User reqUser, User user2);

	public Chat findChatById(Integer chatId) throws Exception;

	public List<Chat> findUsersChat(Integer userId);
}
