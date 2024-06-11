package com.example.minibackend.controller;

import com.example.minibackend.dto.UserDTO;
import com.example.minibackend.entity.User;
import com.example.minibackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userinfo")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        UserDTO userDTO = new UserDTO(0,user.getUserId(), user.getPassword(), user.getName(), user.getBmi());
        return
                userService.addUser(userDTO);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody UserDTO userDTO) {
        User updatedUser = userService.updateUser(userDTO);
        return updatedUser;
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody UserDTO userDTO) {
        userService.deleteUserById(userDTO.getId());
    }
}
