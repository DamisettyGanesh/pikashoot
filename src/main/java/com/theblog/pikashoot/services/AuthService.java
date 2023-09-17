package com.theblog.pikashoot.services;

import com.theblog.pikashoot.dto.UserDTO;
import com.theblog.pikashoot.models.Role;
import com.theblog.pikashoot.models.Users;
import com.theblog.pikashoot.repositories.RoleRepository;
import com.theblog.pikashoot.repositories.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public void registerUser(@NotNull UserDTO userDTO) {
        // Check if the email is already registered
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent() ) {
            throw new RuntimeException("Email/Username is already registered");
        }

        // Create a new Users entity and populate it with user registration data
        Users user = new Users();
        user.setFullName(userDTO.getFullName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());

        // Hash the user's password before storing it in the database
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(hashedPassword);

        //Role
        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        // Save the user to the database
        userRepository.save(user);
    }


    // This method is part of the user details service called during user authentication
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Find a user entity in DB by their username
        // If the user is not found, throw UsernameNotFoundException
        Users user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        // Create and return a UserDetails Object representing the authenticated User
        // This includes their username, password, and grantedAuthorities
        return new User(
                user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles())
        );

    }

    // This method maps the roles of a user to a collection of GrantedAuthority objects.
    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles){
        // Convert the list of roles to a collection of GrantedAuthority objects.
        // Each role is represented as a SimpleGrantedAuthority.
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
