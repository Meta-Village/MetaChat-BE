package com.ohgiraffers.metachatbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class MetaChatBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetaChatBeApplication.class, args);
	}

}
