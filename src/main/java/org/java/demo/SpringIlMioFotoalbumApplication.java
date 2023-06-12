package org.java.demo;

import org.java.demo.auth.pojo.Role;
import org.java.demo.auth.pojo.User;
import org.java.demo.auth.serv.RoleServ;
import org.java.demo.auth.serv.UserServ;
import org.java.demo.pojo.Category;
import org.java.demo.pojo.Image;
import org.java.demo.serv.CategoryServ;
import org.java.demo.serv.ImageServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner{
	
	@Autowired
	private ImageServ imageServ;
	
	@Autowired
	private CategoryServ categoryServ;
	
	@Autowired
	private RoleServ roleServ;
	
	@Autowired
	private UserServ userServ;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Role roleAdmin = new Role("ADMIN");
		Role roleSuperAdmin = new Role("SUPERADMIN");
		
		roleServ.save(roleAdmin);
		roleServ.save(roleSuperAdmin);
		
		
		final String root = new BCryptPasswordEncoder().encode("root");
		final String psw = new BCryptPasswordEncoder().encode("psw");
		
		User userAdmin1 = new User("admin1", root, roleAdmin);
		User userAdmin2 = new User("admin2", root, roleAdmin);
		User userAdmin3 = new User("admin3", root, roleAdmin);
		User userSuperAdmin = new User("superadmin", psw, roleSuperAdmin);
				
		userServ.save(userAdmin1);
		userServ.save(userAdmin2);
		userServ.save(userAdmin3);
		userServ.save(userSuperAdmin);
		
		Category ct1 = new Category("Travel");
		Category ct2 = new Category("Street photography");
		Category ct3 = new Category("Reportage");
		Category ct4 = new Category("Still life");
		Category ct5 = new Category("Landscaping");
		Category ct6 = new Category("Underwater");
		
		categoryServ.save(ct1);
		categoryServ.save(ct2);
		categoryServ.save(ct3);
		categoryServ.save(ct4);
		categoryServ.save(ct5);
		categoryServ.save(ct6);
		
		Image im1 = new Image("Sunrise",
				"a high resolution photo where you can see the sunrise",
				"https://www.nelsalento.com/wp-content/uploads/2014/09/alba-otranto.jpg",
				true,userAdmin1, ct1,ct4,ct5);
		Image im2 = new Image("Barrier Reef",
				"beautiful coral reef image",
				"https://www.nationalgeographic.it/upload/ngi-hero/1_31.jpg",
				true,userAdmin1, ct1,ct6);
		Image im3 = new Image("Melbourne Street Art",
				"a high resolution photo Street Art of Melbourne",
				"https://upload.wikimedia.org/wikipedia/commons/a/aa/Shepard_Fairey_Hosier_Melbourne.jpg",
				true,userAdmin2, ct1,ct2,ct3,ct4);
		Image im4 = new Image("Woods",
				"An enchanting photo of a forest",
				"https://ml1h7kb7lfft.i.optimole.com/ajWRxY4-nsnOK9zJ/w:auto/h:auto/q:90/https://vittime-del-dovere.it/wp-content/uploads/2021/12/natura-1.jpeg",
				true,userAdmin2, ct1,ct4,ct5);
		Image im5 = new Image("Mountain",
				"beautiful mountain landscape",
				"https://travel.thewom.it/content/uploads/sites/4/2022/09/montagna-a-settembre.jpeg",
				true,userAdmin2, ct1,ct4,ct5);
		Image im6 = new Image("Animal",
				"Beautiful photo that takes a specimen of monkey",
				"https://images2-wpc.corriereobjects.it/0NleBhvbT6BiRYCkrklb9nirElo=/fit-in/1050x700/style.corriere.it/assets/uploads/2018/02/Contemplation-Peter-Delaney-Wildlife-Photographer-of-the-Year.jpg",
				true,userAdmin3, ct1,ct3);
		Image im7 = new Image("Iceland",
				"Beautiful photo Iceland",
				"https://www.consigliamidove.it/wp-content/uploads/2021/01/Islanda.jpg",
				true,userAdmin3, ct1,ct3);
		Image im8 = new Image("Life",
				"Life in a photo",
				"https://ugc.futurelearn.com/uploads/images/d4/e8/header_d4e82da6-ca9d-4a4a-aa24-a4d70d004f49.jpg",
				true,userAdmin3, ct1,ct3);
		
		imageServ.save(im1);
		imageServ.save(im2);
		imageServ.save(im3);
		imageServ.save(im4);
		imageServ.save(im5);
		imageServ.save(im6);
		imageServ.save(im7);
		imageServ.save(im8);
		
	}
}


