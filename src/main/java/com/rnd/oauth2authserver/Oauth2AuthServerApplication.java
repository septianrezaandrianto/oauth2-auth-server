package com.rnd.oauth2authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@SpringBootApplication
@ComponentScan({"com.rnd.oauth2authserver.config", "com.rnd.oauth2authserver.controller"})
public class Oauth2AuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2AuthServerApplication.class, args);
	}

}
