package com.example.minibackend.service;

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
  public User addUser(User user) {
    return userRepository.save(user);
  }

  @Transactional
  public User updateUser(User updatedUser) {
    User existingUser = userRepository.findById(updatedUser.getId()).orElse(null);

    if (existingUser == null) {
      throw new RuntimeException("사용자를 찾을 수 없습니다: " + updatedUser.getId());
    }

    existingUser.setBmi(existingUser.getBmi());

  return userRepository.save(existingUser);
  }

  @Transactional
  public void deleteUserById(int userId) {
    userRepository.deleteById(userId);
  }

}
