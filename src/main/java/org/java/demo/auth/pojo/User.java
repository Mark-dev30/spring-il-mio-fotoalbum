package org.java.demo.auth.pojo;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.java.demo.pojo.Image;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class User implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@NotBlank
	private String username;
	
	@NotNull
	@NotBlank
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	@JsonManagedReference
	private List<Image> image;
	
	public User() { }
	public User(String username, String password, Role... roles) {
		
		setUsername(username);
		setPassword(password);
		setRole(roles);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Set<GrantedAuthority> auths = new HashSet<>();
		
		for (Role r : getRoles()) 
			auths.add(new SimpleGrantedAuthority(r.getName()));
		
		return auths;
	}
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}
	@Override
	public boolean isEnabled() {
		
		return true;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public void setRole(Role[] roles) {
		
		setRoles(new HashSet<>(Arrays.asList(roles)));
	}
	
	
	public List<Image> getImage() {
		return image;
	}
	public void setImage(List<Image> image) {
		this.image = image;
	}
	@Override
	public String toString() {
		
		return "[" + getId() + "] " + getUsername();
	}
	
}