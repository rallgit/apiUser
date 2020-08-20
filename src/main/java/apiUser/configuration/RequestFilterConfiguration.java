package apiUser.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import apiUser.service.JwtUserDetailsServiceImpl;

@Component
public class RequestFilterConfiguration extends OncePerRequestFilter{

	 @Autowired
	 private JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;

	 private final JwtToken jwtToken;

	 public RequestFilterConfiguration(JwtToken jwtTokenParam) {
	     this.jwtToken = jwtTokenParam;
	 }

	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	            throws ServletException, IOException {

	        final String requestTokenHeader = request.getHeader("Authorization");
	        String username = null;
	        String jwtTokenString = null;

	        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
	        	jwtTokenString = requestTokenHeader.substring(7);
	            try {
	                username = jwtToken.getUsernameFromToken(jwtTokenString);
	            } catch (IllegalArgumentException e) {
	                System.out.println("No se obtuvo el token");
	            }
	        }

	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            UserDetails userDetails = this.jwtUserDetailsServiceImpl.loadUserByUsername(username);
	            if (jwtToken.validateToken(jwtTokenString, userDetails)) {
	                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	            }
	        }
	        chain.doFilter(request, response);
	    }
	
}
