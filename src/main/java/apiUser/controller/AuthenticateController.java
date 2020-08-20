package apiUser.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import apiUser.DTO.JwtRequestDTO;
import apiUser.configuration.JwtToken;
import apiUser.service.JwtUserDetailsServiceImpl;


@RestController
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;
    
    @Autowired
    private JwtToken jwtToken;

    @RequestMapping(value = "/loginApiUser", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> getAuthenticationToken(@RequestBody JwtRequestDTO jwtRequestDTO) throws Exception {
        
    	try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequestDTO.getEmail(), jwtRequestDTO.getPass()));
        }  catch (Exception e) {
            throw new Exception("Error al Autenticar: ", e);
        }

        final UserDetails userDetails = jwtUserDetailsServiceImpl.loadUserByUsername(jwtRequestDTO.getEmail());
        final String token = jwtToken.generateToken(userDetails);
        
        Map<String,String> responseToken = new HashMap<String, String>();
        responseToken.put("toekn", token);
        
        return ResponseEntity.ok(responseToken);
    }

}
