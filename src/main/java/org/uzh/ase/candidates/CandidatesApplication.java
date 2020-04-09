package org.uzh.ase.candidates;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.uzh.ase.candidates.model.Candidate;
import org.uzh.ase.candidates.repository.CandidateRepository;

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
	private CandidateRepository repository;

	@Override
	public void run(String... args) throws Exception {
		
		repository.save(new Candidate()); // Since the DB should never be written to, we can use the default candidate for testing purposes

	}
}
