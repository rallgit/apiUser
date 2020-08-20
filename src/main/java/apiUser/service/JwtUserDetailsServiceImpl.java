package apiUser.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import apiUser.entity.User;
import apiUser.repository.UserRepository;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;
	
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	
    	User user =  userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no existe!");
        }
        
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
