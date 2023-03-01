
package com.example.pizzeria.Security;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.pizzeria.model.User;
import com.example.pizzeria.repository.UserRepository;


@Service
public class DataBaseUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	
	@Override
    public UserDetails loadUserByUsername(String username) 
    	throws UsernameNotFoundException {
      Optional<User> user = userRepository.findByUsername(username);
      if(user.isPresent()) {
        return new DataBaseUserDetails(user.get());
      } else throw new UsernameNotFoundException("Username not found");
    }
}
