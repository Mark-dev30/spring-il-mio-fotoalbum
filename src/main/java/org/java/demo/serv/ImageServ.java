package org.java.demo.serv;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.java.demo.auth.pojo.User;
import org.java.demo.pojo.Image;
import org.java.demo.repo.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ImageServ {
	@Autowired
	private ImageRepo imageRepo;
	
	public List<Image> findAll(){
		
		return imageRepo.findAll();
	}
	
	public List<Image> findByNameContaining(String title){
		
		return imageRepo.findByTitleContaining(title);
	}
	public List<Image> findByTitleContainingAndUserId(String title, int userid){
		
		return imageRepo.findByTitleContainingAndUserId(title, userid);
	}
	
	public Optional<Image> findById(int id){
		
		return imageRepo.findById(id);
	}
	
	public List<Image> findByUser(User user){
		return imageRepo.findByUser(user);
	}
	
	public Image save(Image image) {
		
		return imageRepo.save(image);
	}
	
	public void deleteImage(Image image) {
		
		 imageRepo.delete(image);
	}
	
	@Transactional
	public Optional<Image> findByIdWithUser(int id){
		
		Optional<Image> imageId = imageRepo.findById(id);
		Hibernate.initialize(imageId.get().getUser());
		
		return imageId;
	}
}
