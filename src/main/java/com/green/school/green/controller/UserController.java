package com.green.school.green.controller;


import com.green.school.green.model.user.Role;
import com.green.school.green.model.user.Users;
import com.green.school.green.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;

import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService usersService;


    @GetMapping("/users")
    public ResponseEntity<List<Users>> getUsers() {
        return ResponseEntity.ok().body(usersService.getUsers());
    }

    @PostMapping("/users/save")
    public ResponseEntity<Users> saveUsers(@RequestBody Users users) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/save").toUriString());
        return ResponseEntity.created(uri).body(usersService.saveUsers(users));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRoles(@RequestBody Role roles) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/roles/save").toUriString());
        return ResponseEntity.created(uri).body(usersService.saveRoles(roles));
    }

    @PostMapping("/role/addtousers")
    public ResponseEntity<?> addRoleToUsers(@RequestBody RoleToUsersForm form) {
        usersService.addRolesToUsers(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }


    @Data
    class RoleToUsersForm {
        private String username;
        private String roleName;
    }}