package de.eldecker.spring.redisdemo;

import static java.util.stream.Collectors.toList;
import static org.springframework.security.crypto.factory.PasswordEncoderFactories.createDelegatingPasswordEncoder;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class Sicherheitskonfiguration {

    /** Rolle, die jedem Nutzer zugefügt wird. */
    private static final String ROLLE_NUTZER = "nutzer";

    /** Array mit Pfaden, auf die auch ohne Authentifizierung zugegriffen werden kann. */
    private final static AntPathRequestMatcher[] OEFFENTLICHE_PFADE_ARRAY = { antMatcher( "/index.html"      ),
                                                                              antMatcher( "/redis-styles.css")
                                                                            };
    
    @Bean
    public SecurityFilterChain httpKonfiguration(HttpSecurity http) throws Exception {

        return http.csrf( (csrf) -> csrf.disable() )
                   .authorizeHttpRequests( auth -> auth.requestMatchers( OEFFENTLICHE_PFADE_ARRAY ).permitAll()
                                                       .anyRequest().authenticated() )
                   .formLogin( formLogin -> formLogin.defaultSuccessUrl( "/inhalt/seite_1.html", true ) )
                   .logout( logout -> logout
                                           .logoutUrl( "/logout" ) 
                                           .logoutSuccessUrl("/abgemeldet.html")
                                           .invalidateHttpSession( true )
                                           .deleteCookies( "JSESSIONID" )
                       )
                   .headers( headers -> headers.disable() ) 
                   .build();
    }
    
    
    /**
     * Nutzernamen mit Passwörtern definieren (werden aus Datenbank geladen).
     *
     * @return Objekt mit allen Nutzernamen und Passwörtern
     */
    @Bean
    public InMemoryUserDetailsManager nutzerLaden() {

        final PasswordEncoder passwordEncoder = createDelegatingPasswordEncoder();
        // Beispielwert Passwort "g3h3im" nach Kodierung: {bcrypt}$2a$10$/KqHJ.PNBaEV4hX2Y4hmDeANEPqJcz4./VoLp1H66DQ
        

        UserDetails nutzer1 = User.withUsername( "alice" )
                                  .password( passwordEncoder.encode( "g3h3im" ) )
                                  .roles( ROLLE_NUTZER )
                                  .build();
        
        UserDetails nutzer2 = User.withUsername( "bob" )
                                  .password( passwordEncoder.encode( "s3cr3t" ) )
                                  .roles( ROLLE_NUTZER )
                                  .build();        

        List<UserDetails> userDetailsList = List.of( nutzer1, nutzer2 );
        
        return new InMemoryUserDetailsManager( userDetailsList );
    }    
    
}
