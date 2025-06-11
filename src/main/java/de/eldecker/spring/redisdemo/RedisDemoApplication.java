package de.eldecker.spring.redisdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Einstiegspunkt der Anwendung.
 */
@SpringBootApplication
public class RedisDemoApplication {

	public static void main( String[] args ) {
		
		SpringApplication.run( RedisDemoApplication.class, args );
	}

}
