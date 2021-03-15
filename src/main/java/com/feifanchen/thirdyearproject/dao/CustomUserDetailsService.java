package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findbyName(name);
        if(user == null){
            throw  new UsernameNotFoundException("user not found");
        }

        return new CustomUserDetails(user);
    }
}
