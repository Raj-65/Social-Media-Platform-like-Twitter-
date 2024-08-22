package com.example.demo;

import com.example.demo.entities.User;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();
        // Map User objects to UserDTO objects containing only name and email fields
        return users.stream()
                .map(user -> new UserDTO(user.getName(), user.getUserID(), user.getEmail()))
                .collect(Collectors.toList());
    }
}
