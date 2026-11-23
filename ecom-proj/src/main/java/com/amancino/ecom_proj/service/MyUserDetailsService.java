package com.amancino.ecom_proj.service;

import com.amancino.ecom_proj.model.UserPrincipal;
import com.amancino.ecom_proj.model.Users;
import com.amancino.ecom_proj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username);
        if (user == null) {
            // user not found
            throw new UsernameNotFoundException("user not found");
        }

        return new UserPrincipal(user);
    }
}
