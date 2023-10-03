package com.jorge.jwtpractice;

import com.jorge.jwtpractice.model.dto.RegisterRequest;
import com.jorge.jwtpractice.repository.UserRepository;
import com.jorge.jwtpractice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.jorge.jwtpractice.model.Role.*;

@SpringBootApplication
public class JwtpracticeApplication implements CommandLineRunner {
	@Autowired
	UserRepository userRepo;
	@Autowired
	AuthService service;

	public static void main(String[] args) {
		SpringApplication.run(JwtpracticeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var admin = RegisterRequest.builder()
				.firstname("Admin")
				.lastname("Admin")
				.email("admin@mail.com")
				.password("password")
				.role(ADMIN)
				.build();
		System.out.println("Admin token: " + service.register(admin).getToken());

		var manager = RegisterRequest.builder()
				.firstname("Manager")
				.lastname("Manager")
				.email("manager@mail.com")
				.password("password")
				.role(MANAGER)
				.build();
		System.out.println("Manager token: " + service.register(manager).getToken());

		var user = RegisterRequest.builder()
				.firstname("User")
				.lastname("User")
				.email("user@mail.com")
				.password("password")
				.role(USER)
				.build();
		System.out.println("User token: " + service.register(user).getToken());
	}
}
