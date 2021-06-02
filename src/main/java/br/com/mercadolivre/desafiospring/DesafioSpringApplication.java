package br.com.mercadolivre.desafiospring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class DesafioSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioSpringApplication.class, args);
	}

	@Autowired
	public void configureJackson(ObjectMapper objectMapper) {
		objectMapper.setTimeZone(TimeZone.getDefault());
	}

}
