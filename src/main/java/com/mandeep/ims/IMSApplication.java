package com.mandeep.ims;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class IMSApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(IMSApplication.class, args);
	}

}
