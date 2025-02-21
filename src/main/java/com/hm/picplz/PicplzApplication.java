package com.hm.picplz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PicplzApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicplzApplication.class, args);
	}
}
