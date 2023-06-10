package org.java.demo.serv;

import java.util.List;
import java.util.Optional;

import org.java.demo.pojo.Category;
import org.java.demo.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServ {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	public List<Category> findAll(){
		
		return categoryRepo.findAll();
	}
	
	public Optional<Category> findById(int id){
		
		return categoryRepo.findById(id);
	}
	
	public Category save(Category category) {
		
		return categoryRepo.save(category);
	}
	
	public void deleteCategory(Category category) {
		
		categoryRepo.delete(category);
	}

}
