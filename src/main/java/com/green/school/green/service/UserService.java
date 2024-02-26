package com.green.school.green.service;


import com.green.school.green.model.user.Role;
import com.green.school.green.model.user.Users;

import java.util.List;


public interface UserService {
    Users saveUsers(Users user);
    Role saveRoles(Role role);
    void addRolesToUsers(String username,String roleName);
    Users getUsers(String username);
    List<Users> getUsers();
}
