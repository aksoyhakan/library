package com.example.library.service;

import com.example.library.repository.RoleRepository;
import com.example.library.repository.UserRepository;
import com.example.library.user.ApplicationUser;
import com.example.library.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ApplicationUser register(String firstName, String lastName, String email, String password){

        String encodedPassword= passwordEncoder.encode(password);
        Role userRole=roleRepository.findByAuthority("USER").orElseThrow();

        Set<Role> authorities=new HashSet<>();
        authorities.add(userRole);
        ApplicationUser user=new ApplicationUser();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setAuthorities(authorities);

        return userRepository.save(user);

    }
}
