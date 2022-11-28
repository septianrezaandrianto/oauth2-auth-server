package com.rnd.oauth2authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@EnableResourceServer
@SpringBootApplication
@ComponentScan({"com.rnd.oauth2authserver.config", "com.rnd.oauth2authserver.controller",
		"com.rnd.oauth2authserver.service"})
@RestController
public class Oauth2AuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2AuthServerApplication.class, args);
	}

	@PostMapping("/validateUser")
	public Principal user(Principal user) {
		return user;
	}
}
