package com.uniqhorn.config;

import com.uniqhorn.entity.User;
import com.uniqhorn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author erizov This class is used for Security (Roles)
 */


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        CustomUserDetails userDetails = null;

        if (user != null) {
            userDetails = new CustomUserDetails();
            userDetails.setUser(user);
            ;
        } else {
            throw new UsernameNotFoundException("User not exists with name: " + username);
        }
        return userDetails;
    }
}
