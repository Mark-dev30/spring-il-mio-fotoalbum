package org.java.demo.serv;

import org.java.demo.pojo.Message;
import org.java.demo.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServ {

	@Autowired
	private MessageRepo messageRepo;
	
	public Message save(Message message) {
		
		return messageRepo.save(message);
	}
}
