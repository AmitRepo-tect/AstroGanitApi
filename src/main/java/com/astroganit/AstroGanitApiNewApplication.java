package com.astroganit;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.astroganit.api.entities.Role;
import com.astroganit.api.repository.RoleRepo;

@SpringBootApplication
@EnableCaching

public class AstroGanitApiNewApplication implements CommandLineRunner {
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(AstroGanitApiNewApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public void run(String... args) throws Exception {
		Role role1 = new Role();
		role1.setId(501);
		role1.setName("ROLE_ADMIN");
		Role role2 = new Role();
		role2.setId(502);
		role2.setName("ROLE_NORMAL");
		this.roleRepo.save(role1);
		this.roleRepo.save(role2);
	}
}
