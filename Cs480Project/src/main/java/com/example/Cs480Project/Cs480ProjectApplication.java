package com.example.Cs480Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;


@SpringBootApplication
public class Cs480ProjectApplication implements CommandLineRunner {
	/*@Autowired
	private JdbcTemplate jdbcTemplate; */

	public static void main(String[] args) {
		SpringApplication.run(Cs480ProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}

