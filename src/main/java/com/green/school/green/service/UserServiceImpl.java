package com.green.school.green.service;

import com.green.school.green.model.user.Role;
import com.green.school.green.model.user.Users;
import com.green.school.green.repository.RoleRepository;
import com.green.school.green.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User in database:{}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public Users saveUsers(Users user) {
        log.info("saving new user{} to the database", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRoles(Role role) {
        log.info("saving new role{} to the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRolesToUsers(String username, String roleName) {
        log.info("Adding role{}to user{}", roleName, username);
        Users users = userRepository.findByUsername(username);
        Role roles = roleRepository.findByName(roleName);
        users.getRoles().add(roles);
    }

    @Override
    public Users getUsers(String username) {
        log.info("Fecting user{}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<Users> getUsers() {
        log.info("Fecting all users{}");
        return userRepository.findAll();
    }


}
