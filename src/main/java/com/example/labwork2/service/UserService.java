package com.example.labwork2.service;

import com.example.labwork2.model.User;
import com.example.labwork2.model.Role;
import com.example.labwork2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public User createUser(User user){
        // Кодируем пароль перед сохранением
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails){
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setUsername(userDetails.getUsername());
            
            if(userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()){
                user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            }

            // ✅ Правильное обновление ролей (если переданы)
            if (userDetails.getRoles() != null && !userDetails.getRoles().isEmpty()) {
                user.setRoles(userDetails.getRoles());
            }

            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
