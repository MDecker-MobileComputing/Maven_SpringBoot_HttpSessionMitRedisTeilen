package de.eldecker.spring.redisdemo;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Klasse, um jeden einzelnen HTTP-Request für statische Ressourcen
 * (den nur solche liefert die Anwendung aus) auf einen Logger zu schreiben,
 * damit man sieht, dass der <i>Load Balancer</i> die Requests auf mehrere
 * Instanzen verteilt.
 */
@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

	private static Logger LOG = LoggerFactory.getLogger( RequestLoggingFilter.class );
	
	
	/**
	 * Methode schreibt für jeden Request eine Nachricht auf dne Logger.
	 * 
	 * Beispiel für Log-Nachricht:
	 * <pre>
	 * HTTP-Request erhalten: GET /inhalt/seite_1.html
	 * </pre> 
	 */
    @Override
    protected void doFilterInternal( HttpServletRequest request,
                                     HttpServletResponse response,
                                     FilterChain filterChain )
            throws ServletException, IOException {

    	LOG.info( "HTTP-Request erhalten: {} {}", request.getMethod(), request.getRequestURI() );
    	
        filterChain.doFilter( request, response );
    }
    
}