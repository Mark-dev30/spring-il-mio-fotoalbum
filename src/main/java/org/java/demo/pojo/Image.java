package org.java.demo.pojo;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Title can't be null")
	private String title;
	
	@NotBlank(message = "Description can't be null")
	private String description;
	
	@NotBlank(message = "Url can't be null")
	private String url;
	@NotNull
	private boolean visible;
	
	@ManyToMany
	private List<Category> categories;
	
	public Image() {};
	
	public Image(String title, String description, String url, boolean visible, Category...categories) {
		setTitle(title);
		setDescription(description);
		setUrl(url);
		setVisible(visible);
		setNewCategories(categories);
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public void setNewCategories(Category[] categories) {
		setCategories(Arrays.asList(categories));
	}
	public void addCategory(Category category) {
		getCategories().add(category);
	}
	
	public void removeImage(Category category) {
		
		getCategories().remove(category);
	}
	
	@Override
	public String toString() {
		
		return "[" + getId() + "]" + getTitle();
	}
	
}
