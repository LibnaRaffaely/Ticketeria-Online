package com.project.ticketeria.modules.userBasic.useCases;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.ticketeria.modules.userBasic.UserBasicRepository;

@Service
public class UserDetailsImp implements UserDetailsService {

    private final UserBasicRepository userBasicRepository;

    public UserDetailsImp(UserBasicRepository userBasicRepository) {
        this.userBasicRepository = userBasicRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userBasicRepository.findByEmail(email)
                .map(user -> (UserDetails) user)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by Email"));
    }

}
