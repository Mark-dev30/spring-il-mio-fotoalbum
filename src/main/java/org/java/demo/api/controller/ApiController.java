package org.java.demo.api.controller;

import java.util.List;

import org.java.demo.pojo.Image;
import org.java.demo.pojo.Message;
import org.java.demo.serv.ImageServ;
import org.java.demo.serv.MessageServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class ApiController {
	@Autowired
	private ImageServ imageServ;
	@Autowired
	private MessageServ messageServ;

	@GetMapping("/images")
	public ResponseEntity<List<Image>> getImage(@RequestParam(required = false) String title){
		
		if(title == null) {
			List<Image> images = imageServ.findAll();
			return new ResponseEntity<>(images, HttpStatus.OK);
		}
		else {
			List<Image> images = imageServ.findByNameContaining(title);
			
			return new ResponseEntity<>(images, HttpStatus.OK);
		}
		
		
	}
	@PostMapping("/message")
	public ResponseEntity<Message> createMessage(
			@RequestBody Message message
		){
		message = messageServ.save(message);
		
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
}
