package com.clinic.clinic.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.clinic.clinic.entity.User;
import com.clinic.clinic.repository.UserRepository;

/**
 * This customized class which loads user-specific data.
 * @author priyanshu.goyal
 *
 */
public class CustomUserDetailService implements UserDetailsService {
 
    @Autowired
    private UserRepository userRepo;
     
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomeUserDetails(user);
    }

}
