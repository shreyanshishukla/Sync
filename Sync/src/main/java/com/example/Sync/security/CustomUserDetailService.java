package com.example.Sync.security;

import com.example.Sync.model.User;
import com.example.Sync.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private  UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user= userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail).orElseThrow(()-> new UsernameNotFoundException("user doesn't exists by username or email"));
        Set<GrantedAuthority> authoritySet=new HashSet<>(Arrays.asList(new SimpleGrantedAuthority("USER")));
        return new org.springframework.security.core.userdetails.User(usernameOrEmail,user.getPassword(),authoritySet);
    }
}
