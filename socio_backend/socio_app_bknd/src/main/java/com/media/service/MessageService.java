package com.media.service;

import java.util.List;

import com.media.models.Message;
import com.media.models.User;

public interface MessageService {

	public Message createMessage(User user, Integer chatId, Message message) throws Exception;

	public List<Message> findChatsMessages(Integer chatId) throws Exception;
}
