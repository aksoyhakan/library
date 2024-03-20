package com.example.library;

import com.example.library.repository.RoleRepository;
import com.example.library.repository.UserRepository;
import com.example.library.user.ApplicationUser;
import com.example.library.user.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository,
						  PasswordEncoder passwordEncoder){

		return args -> {

			if(roleRepository.findByAuthority("ADMIN").isPresent()){
				return;
			}

			Role adminRole = new Role();
			adminRole.setAuthority("ADMIN");
			Role userRole=new Role();
			userRole.setAuthority("USER");

			roleRepository.save(adminRole);
			roleRepository.save(userRole);

			Set<Role> roles=new HashSet<>();

			roles.add(adminRole);

			ApplicationUser admin=new ApplicationUser();
			admin.setFirstName("Hakan");
			admin.setLastName("AKSOY");
			admin.setEmail("haksoy@test.com");
			admin.setPassword(passwordEncoder.encode("123456"));
			admin.setAuthorities(roles);
			userRepository.save(admin);
		};
	}
}
