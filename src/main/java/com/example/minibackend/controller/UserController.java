package com.example.minibackend.controller;

import com.example.minibackend.dto.User.UserDTO;
import com.example.minibackend.dto.User.UserUpdateDTO;
import com.example.minibackend.entity.User;
import com.example.minibackend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/userinfo")
@RequiredArgsConstructor
@Validated
public class UserController {
  private final UserService userService;

  @GetMapping
  public List<User> getUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/info")
  public Optional<User> getUser(@RequestParam("userId") String userId) {
    return userService.findByUserId(userId);
  }

  @PostMapping("/add")
  public User addUser(@RequestBody User user) {
    UserDTO userDTO = new UserDTO(0,user.getUserId(), user.getPassword(), user.getName(), user.getBmi());
    return userService.addUser(userDTO);
  }

  @PutMapping("/update")
  public UserDTO updateUser(@RequestParam("id") int id,@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
    User user = userService.getUserById(id);

    user.setBmi(userUpdateDTO.getBmi());

    User updatedUser = userService.updateUser(user);

    return new UserDTO(
        updatedUser.getId(),
        updatedUser.getUserId(),
        updatedUser.getPassword(),
        updatedUser.getName(),
        updatedUser.getBmi()
    );
  }

  @DeleteMapping("/delete")
  public void deleteUser(@RequestParam("id") int id) {
    userService.deleteUserById(id);
  }
}
