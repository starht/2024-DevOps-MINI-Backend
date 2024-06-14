package com.example.minibackend.service;

import com.example.minibackend.dto.User.UserDTO;
import com.example.minibackend.entity.User;
import com.example.minibackend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final FavoriteFoodRepository favoriteFoodRepository;
  private final FavoriteExerciseRepository favoriteExerciseRepository;
  private final IntakeCalorieRepository intakeCalorieRepository;
  private final BurnCalorieRepository burnCalorieRepository;
  private final CalorieRepository calorieRepository;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User getUserById(int id) {
    User user = userRepository.findById(id).
            orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + id));
    return user;
  }

  public Optional<User> findByUserId(String userId) {
    userRepository.findByUserId(userId).orElseThrow(()-> new RuntimeException("사용자를 찾을 수 없습니다: " + userId));
    return userRepository.findByUserId(userId);
  }

  @Transactional
  public User addUser(UserDTO userDTO) {
    User user = new User(0, userDTO.getUserId(), userDTO.getPassword(), userDTO.getName(), userDTO.getBmi());
    return userRepository.save(user);
  }

  @Transactional
  public User updateUser(User user) {
    User existingUser = userRepository.findById(user.getId())
            .orElseThrow(()-> new RuntimeException("사용자를 찾을 수 없습니다: " + user.getId()));
    existingUser.setBmi(user.getBmi());

    return userRepository.save(existingUser);
  }

  @Transactional
  public void deleteUserById(int userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + userId));

    favoriteFoodRepository.deleteAllByUser(user);
    favoriteExerciseRepository.deleteAllByUser(user);
    intakeCalorieRepository.deleteAllByUser(user);
    burnCalorieRepository.deleteAllByUser(user);
    calorieRepository.deleteAllByUser(user);

    userRepository.delete(user);
  }

}
