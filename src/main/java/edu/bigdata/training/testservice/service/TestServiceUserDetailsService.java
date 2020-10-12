package edu.bigdata.training.testservice.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class TestServiceUserDetailsService implements UserDetailsService {


    public TestServiceUserDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return org.springframework.security.core.userdetails.User.builder()
                .username(username)
                .password("")
                .authorities(new ArrayList<>())
                .build();
    }
}
