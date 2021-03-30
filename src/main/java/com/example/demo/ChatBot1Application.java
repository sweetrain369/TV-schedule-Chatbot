package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
public class ChatBot1Application {
	public static void main(String[] args) {
		SpringApplication.run(ChatBot1Application.class, args);
	}
}