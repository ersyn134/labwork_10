package com.example.labwork2.controller;

import com.example.labwork2.model.User;
import com.example.labwork2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Получение всех пользователей доступно ADMIN и USER
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    // Получение пользователя по ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Optional<User> getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    // Создание нового пользователя – доступно только ADMIN
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    // Обновление пользователя – доступно только ADMIN
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

    // Удаление пользователя – доступно только ADMIN
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
