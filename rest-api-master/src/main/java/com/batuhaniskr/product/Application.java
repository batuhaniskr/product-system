package com.batuhaniskr.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Application {

	@Bean
	   public BCryptPasswordEncoder passwordEncoder(){
			return new BCryptPasswordEncoder();
	}
	
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
