package de.eldecker.spring.redisdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


/**
 * Einstiegspunkt der Anwendung.
 * <br><br>
 *
 * https://docs.spring.io/spring-session/reference/guides/boot-redis.html
 */
@SpringBootApplication
//@EnableRedisHttpSession
public class RedisDemoApplication {

	public static void main( String[] args ) {

		SpringApplication.run( RedisDemoApplication.class, args );
	}

}
