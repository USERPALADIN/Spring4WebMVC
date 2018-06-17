package com.mitya.controller.rest;

import com.mitya.model.Role;
import com.mitya.model.User;
import com.mitya.service.RoleService;
import com.mitya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {
    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "/users_all")
    public ResponseEntity<List<User>> showUsers() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @RequestMapping(value = "/delete_user", method = RequestMethod.POST)
    public ResponseEntity deleteUser(@RequestBody  User userWithId) {
        long id =  userWithId.getId();
        userService.delete(id);
     //   List<User> users = userService.getAll();
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/update_user", method = RequestMethod.POST)
    public ResponseEntity updateUser(@RequestBody User user) {
        List<Role> userRoles = (List<Role>) user.getRoles();
        List<Role> resultRoles = new ArrayList<>();
        for (Role userRole : userRoles) {
            Role role = roleService.getByName(userRole.getName());
            resultRoles.add(role);
        }
        user.setRoles(resultRoles);
        userService.update(user);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/insert_user", method = RequestMethod.PUT)
    public ResponseEntity insertUser(@RequestBody User user) {
        String name_Role = "ROLE_USER";
        Collection<Role> roles = new ArrayList<>();
        Role role = roleService.getByName(name_Role);
        roles.add(role);
        user.setRoles(roles);
        userService.insert(user);

        return ResponseEntity.ok().build();
    }
}
