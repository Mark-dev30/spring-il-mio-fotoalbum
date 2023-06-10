package org.java.demo.repo;

import java.util.List;

import org.java.demo.auth.pojo.User;
import org.java.demo.pojo.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<Image, Integer> {
	public List<Image> findByTitleContaining(String title);
	public List<Image> findByUser(User user);
}
