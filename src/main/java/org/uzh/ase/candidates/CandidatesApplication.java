package org.uzh.ase.candidates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.uzh.ase.candidates.model.Candidates;
import org.uzh.ase.candidates.repository.CandidatesRepository;

@SpringBootApplication
public class CandidatesApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CandidatesApplication.class);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/example").allowedOrigins("http://localhost:8080");
			}
		};
	}

	@Autowired
	private CandidatesRepository repository;

	@Override
	public void run(String... args) throws Exception {
		repository.deleteAll();
		repository.save(new Candidates("Toy Story 1", "Toy Story 2", "Toy Story 3"));
	}
}
