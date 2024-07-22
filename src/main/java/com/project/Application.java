package com.project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.models.model.Product;
import com.project.models.model.Role;
import com.project.models.model.User;
import com.project.models.repository.ProductRepository;
import com.project.models.repository.RoleRepository;
import com.project.models.repository.UserRepository;
import com.project.models.service.security.CryptEncoder;

@SpringBootApplication
public class Application {

	private static UserRepository userRepository;
	
	private static RoleRepository roleRepository;
	
	private static ProductRepository productRepository;
	
	private static CryptEncoder pwdEncoder;
	
	@Autowired
	public Application(UserRepository userRepository, RoleRepository roleRepository, 
			ProductRepository productRepository, CryptEncoder pwdEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.productRepository = productRepository;
		this.pwdEncoder = pwdEncoder;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		if(!isAlreadyCreated()) {
			CreateUsers();
			CreateRoles();
			CreateProducts();
			CreateProducts();
			AlterSellerRoles();
		}
	}
	
	private static boolean isAlreadyCreated() {
		return userRepository.findById(1L).isPresent();
	}
	
	private static void CreateUsers() {
		
		User user = new User();
		user.setId(1L);
		user.setEmail("user@teste.com");
		user.setUsername("User");
		user.setPassword(pwdEncoder.encryptPass("123456"));
		
		User seller = new User();
		seller.setId(2L);
		seller.setEmail("seller@teste.com");
		seller.setUsername("Seller");
		seller.setPassword(pwdEncoder.encryptPass("123456"));
		
		userRepository.saveAndFlush(user);
		userRepository.saveAndFlush(seller);
	}
	
	private static void CreateRoles() {
		
		Role userRole = new Role();
		userRole.setId(1L);
		userRole.setRole("USER");
		userRole.setDescription("Usuário padrão do sistema");
		
		Role adminRole = new Role();
		adminRole.setId(2L);
		adminRole.setRole("ADMIN");
		adminRole.setDescription("Administrador");
		
		Role sellerRole = new Role();
		sellerRole.setId(3L);
		sellerRole.setRole("SELLER");
		sellerRole.setDescription("Vendedor");
		
		roleRepository.saveAndFlush(userRole);
		roleRepository.saveAndFlush(adminRole);
		roleRepository.saveAndFlush(sellerRole);
		
	}
	
	private static void CreateProducts() {
		
		User seller = new User();
		seller.setId(2L);
		
		Product cpu = new Product();
		cpu.setId(1L);
		cpu.setCategory("Processador");
		cpu.setDescription("AMD >>> Intel (é zoeira <3)");
		cpu.setPrice(999.24);
		cpu.setUser(seller);
		
		Product gpu = new Product();
		gpu.setId(2L);
		gpu.setCategory("Placa de vídeo");
		gpu.setDescription("AMD >>> Nvidia (Eric, bota um confia, nãããoo)");
		gpu.setPrice(3123.97);
		gpu.setUser(seller);
		
		productRepository.saveAndFlush(cpu);
		productRepository.saveAndFlush(gpu);
		
	}
	
	private static void AlterSellerRoles() {
		
		User user = userRepository.findById(1L).orElseThrow();
		User seller = userRepository.findById(2L).orElseThrow();
		Role userRole = roleRepository.findByRole("USER").orElseThrow();
		Role sellerRole = roleRepository.findByRole("SELLER").orElseThrow();
		Role adminRole = roleRepository.findByRole("ADMIN").orElseThrow();
		
		List<Role> userRoles = new ArrayList<Role>();
		userRoles.add(userRole);
		
		List<Role> sellerRoles = new ArrayList<Role>();
		sellerRoles.add(userRole);
		sellerRoles.add(sellerRole);
		sellerRoles.add(adminRole);
		
		user.setRoles(userRoles);
		seller.setRoles(sellerRoles);
		
		userRepository.saveAndFlush(user);
		userRepository.saveAndFlush(seller);
	}

}
