package org.uzh.ase.candidates;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


/**
 * Main class initiating the exuction of the application with correct configuration.
 */
@SpringBootApplication
@PropertySource({ "classpath:application-${env:local}.properties" })
public class CandidatesApplication {
	
	/**
	 * Main method that starts the spring application.
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(CandidatesApplication.class);
	}

}
