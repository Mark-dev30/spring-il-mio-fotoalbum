package org.java.demo.controller;

import java.awt.image.FilteredImageSource;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.java.demo.auth.pojo.User;
import org.java.demo.auth.pojo.Role;
import org.java.demo.auth.repo.UserRepo;
import org.java.demo.auth.serv.UserServ;
import org.java.demo.pojo.Category;
import org.java.demo.pojo.Image;
import org.java.demo.serv.CategoryServ;
import org.java.demo.serv.ImageServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class ImageController {

	@Autowired
	private ImageServ imageServ;
	
	@Autowired
	private CategoryServ categoryServ;
	@Autowired
	private UserServ userServ;
	
	@GetMapping("/")
	public String getHome(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		User user = userServ.findByUsernameWithImage(userName).get();
		boolean isAdmin = authentication.getAuthorities().stream()
	            .anyMatch(role -> role.getAuthority().equals("ADMIN"));
	    
	    if (isAdmin) {
	    	List<Image> imagesuser = user.getImage();
	    	List<Image> images = imagesuser.stream()
                    .filter(image -> image.isVisible())
                    .collect(Collectors.toList());
	    	
	    	
	    	
	    	model.addAttribute("images",images);
	    } else {
	        
	      List<Image> images = imageServ.findAll();
	      model.addAttribute("images",images);
	    }
		
		
		
		return "index";
	}
	
	@PostMapping("/image/title")
	public String getImageByTitle(Model model,@RequestParam(required = false) String title) {
		List<Image> images = imageServ.findByNameContaining(title);
		model.addAttribute("images", images);
		model.addAttribute("title", title);
		
		return "index";
	}
	@GetMapping("/show/{id}")
	public String showImg(Model model,@PathVariable int id) {
		Image image = imageServ.findById(id).get();
		model.addAttribute("image", image);
		
		return "img-show";
	}
	
	@GetMapping("/create")
	public String getImgCreate(Model model) {
		List<Category> categories = categoryServ.findAll();
		model.addAttribute("image", new Image());
		model.addAttribute("categories", categories);
		
		return "img-create";
	}
	
	@PostMapping("/create")
	public String storeImageCreate(Model model, @Valid @ModelAttribute Image image, BindingResult bindingResult) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		User user = userServ.findByUsernameWithImage(userName).get();
		image.setUser(user);
		if(bindingResult.hasErrors()) {
			
			List<Category> categories = categoryServ.findAll();
			model.addAttribute("image", image);
			model.addAttribute("categories", categories);
			model.addAttribute("errors", bindingResult);
			return "img-create";
		}
		if( authentication.getAuthorities().stream()
	            .anyMatch(role -> role.getAuthority().equals("ADMIN"))){
			image.setVisible(true);
		}
		
		imageServ.save(image);
		
		return "redirect:/";
	}
	
	@GetMapping("/update/{id}")
	public String editImage(Model model, @PathVariable int id) {
		List<Category> categories = categoryServ.findAll();
		List<User> users = userServ.findAll();
		Image image = imageServ.findById(id).get();
		model.addAttribute("image", image);
		model.addAttribute("categories", categories);
		model.addAttribute("users", users);
		
		return "img-update";
	}
	
	@PostMapping("/update/{id}")
	public String updateImage(Model model, @PathVariable int id, @Valid @ModelAttribute Image image, BindingResult bindingResult) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<Category> categories = categoryServ.findAll();
		System.err.println(image.getUser());
		if(bindingResult.hasErrors()) {
			model.addAttribute("image",image);
			model.addAttribute("categories", categories);
			model.addAttribute("errors", bindingResult);
			
			return "img-update";
			
		}
		if( authentication.getAuthorities().stream()
	            .anyMatch(role -> role.getAuthority().equals("ADMIN"))){
			image.setVisible(true);
		}
		imageServ.save(image);
		
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteImage(@PathVariable int id) {
		Image image = imageServ.findById(id).get();
		imageServ.deleteImage(image);
		
		return "redirect:/";
	}
}
