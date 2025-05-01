package com.deepak.blog;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class BlogAppApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApiApplication.class, args);
	}
    @Bean
    ModelMapper getModelMapper()
	{
		return new ModelMapper();
	}
}
