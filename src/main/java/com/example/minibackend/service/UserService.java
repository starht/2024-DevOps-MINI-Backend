package com.example.minibackend.service;

import com.example.minibackend.dto.UserDTO;
import com.example.minibackend.entity.User;
import com.example.minibackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User getUserById(int id) {
    return userRepository.findById(id).get();
  }

  @Transactional
  public User addUser(UserDTO userDTO) {
    User user = new User(0,userDTO.getUserId(), userDTO.getPassword(), userDTO.getName(), userDTO.getBmi());
    return userRepository.save(user);
  }

  @Transactional
  public User updateUser(UserDTO updatedUserDTO) {
    User user = userRepository.findById(updatedUserDTO.getId()).get();
    user.setBmi(updatedUserDTO.getBmi());

  return userRepository.save(user);
  }

  @Transactional
  public void deleteUserById(int userId) {
    userRepository.deleteById(userId);
  }

}
