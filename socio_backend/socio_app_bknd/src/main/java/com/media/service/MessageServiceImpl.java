package com.media.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.media.models.Chat;
import com.media.models.Message;
import com.media.models.User;
import com.media.repository.ChatRepository;
import com.media.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private ChatRepository chatRepository;

	@Autowired
	private ChatService chatService;

	@Override
	public Message createMessage(User user, Integer chatId, Message message) throws Exception {

		Chat chat = chatService.findChatById(chatId);

		Message newMessage = new Message();

		newMessage.setChat(chat);
		newMessage.setContent(message.getContent());
		newMessage.setImage(message.getImage());
		newMessage.setUser(user);
		newMessage.setTimeStamp(LocalDateTime.now());
		
		Message savedMessage = messageRepository.save(newMessage);
		
		chat.getMessages().add(savedMessage);
		
		chatRepository.save(chat);

		return savedMessage;
	}

	@Override
	public List<Message> findChatsMessages(Integer chatId) throws Exception {

		Chat chat = chatService.findChatById(chatId);

		return messageRepository.findByChatId(chatId);
	}

}
