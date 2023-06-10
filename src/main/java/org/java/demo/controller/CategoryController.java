package org.java.demo.controller;

import java.util.List;

import org.java.demo.pojo.Category;
import org.java.demo.pojo.Image;
import org.java.demo.serv.CategoryServ;
import org.java.demo.serv.ImageServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import jakarta.validation.Valid;

@Controller
public class CategoryController {

	@Autowired
	private ImageServ imageServ;
	
	@Autowired
	private CategoryServ categoryServ;
	
	@GetMapping("/category")
	public String getCategory(Model model) {
		List<Category> categories = categoryServ.findAll();
		model.addAttribute("categories",categories);
		
		return "category-index";
	}
	@GetMapping("/category/create")
	public String getImgCreate(Model model) {
		List<Category> categories = categoryServ.findAll();
		model.addAttribute("pizza", new Image());
		model.addAttribute("categories", categories);
		
		return "category-create";
	}
	@PostMapping("/category/create")
	public String storeCAtegoryCreate(Model model, @Valid @ModelAttribute Category category, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("category", category);
			model.addAttribute("errors", bindingResult);
			return "img-create";
		}
		
		categoryServ.save(category);
		
		return "redirect:/category";
	}
	
	
	@GetMapping("/category/update/{id}")
	public String editCategory(Model model, @PathVariable int id) {
		Category category = categoryServ.findById(id).get();
		model.addAttribute("category", category);
		
		return "category-update";
	}
	
	@PostMapping("/category/update/{id}")
	public String updateCategory(Model model, @PathVariable int id, @Valid @ModelAttribute Category category, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("category",category);
			model.addAttribute("errors", bindingResult);
			
			return "category-update";
			
		}
		categoryServ.save(category);
		
		return "redirect:/category";
	}
	
	@GetMapping("/category/delete/{id}")
	public String deleteCategory(@PathVariable int id) {
		Category category = categoryServ.findById(id).get();
		
		for (Image i : category.getImages()) {
			i.removeImage(category);
			imageServ.save(i);
			
		}
		categoryServ.deleteCategory(category);
		
		return "redirect:/category";
	}
}
