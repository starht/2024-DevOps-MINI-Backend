package com.example.minibackend.service;

import com.example.minibackend.entity.BurnCalorie;
import com.example.minibackend.entity.User;
import com.example.minibackend.repository.BurnCalorieRepository;
import com.example.minibackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BurnCalorieService {
  private final BurnCalorieRepository burnCalorieRepository;
  private final UserRepository userRepository;

  public List<BurnCalorie> getAllBurnCalories() {
    List<BurnCalorie> burnCalories = burnCalorieRepository.findAll();
    for (BurnCalorie burnCalorie : burnCalories) {
      Hibernate.initialize(burnCalorie.getUser());
    }
    return burnCalories;
  }

  public List<BurnCalorie> getBurnCalorieByUser(String userId) {
    Optional<User> byUserId = userRepository.findByUserId(userId);
    if (byUserId.isPresent()) {
      User user = byUserId.get();
      List<BurnCalorie> burnCalories = burnCalorieRepository.findAllByUser(user);
      for (BurnCalorie burnCalorie : burnCalories) {
        Hibernate.initialize(burnCalorie.getUser());
      }
      return burnCalories;
    }
    throw new RuntimeException("사용자를 찾을 수 없습니다: " + userId);
  }

  public Optional<BurnCalorie> findById(int burnId) {
    Optional<BurnCalorie> burnCalorie = burnCalorieRepository.findById(burnId);
    if (burnCalorie.isPresent()) {
      Hibernate.initialize(burnCalorie.get().getUser());
    }
    return burnCalorie;
  }

  public Optional<BurnCalorie> findByUserId(String userId) {
    Optional<User> byUserId = userRepository.findByUserId(userId);
    if (byUserId.isPresent()) {
      Optional<BurnCalorie> burnCalorie = Optional.ofNullable(burnCalorieRepository.findByUser(byUserId.get()));
      if (burnCalorie.isPresent()) {
        Hibernate.initialize(burnCalorie.get().getUser());
      }
      return burnCalorie;
    } else {
      throw new RuntimeException("사용자를 찾을 수 없습니다: " + userId);
    }
  }

  public Optional<BurnCalorie> findByUserIdAndDate(String userId, LocalDate date) {
    Optional<User> byUserId = userRepository.findByUserId(userId);
    if (byUserId.isPresent()) {
      Optional<BurnCalorie> burnCalorie = Optional.ofNullable(burnCalorieRepository.findByUserAndDate(byUserId.get(), date));
      if (burnCalorie.isPresent()) {
        Hibernate.initialize(burnCalorie.get().getUser());
      }
      return burnCalorie;
    } else {
      throw new RuntimeException("사용자를 찾을 수 없습니다: " + userId);
    }
  }

  @Transactional
  public BurnCalorie addBurnCalorie(BurnCalorie burnCalorie) {
    Optional<User> userOptional = userRepository.findByUserId(burnCalorie.getUser().getUserId());
    if (userOptional.isPresent()) {
      burnCalorie.setUser(userOptional.get());
      burnCalorie.setCalorie(burnCalorie.getCalorie());
      return burnCalorieRepository.save(burnCalorie);
    }else {
      throw new RuntimeException("사용자를 찾을 수 없습니다: " + burnCalorie.getUser().getUserId());
    }
  }

  @Transactional
  public BurnCalorie updateBurnCalorie(BurnCalorie burnCalorie) {
    BurnCalorie existingBurnCalorie = burnCalorieRepository.findById(burnCalorie.getBurnId()).orElse(null);

    if (existingBurnCalorie == null) {
      throw new RuntimeException("소모 칼로리를 찾을 수 없습니다: " + burnCalorie.getBurnId());
    }

    existingBurnCalorie.setCalorie(burnCalorie.getCalorie());

    Optional<User> userOptional = userRepository.findByUserId(burnCalorie.getUser().getUserId());
    if (userOptional.isPresent()) {
      existingBurnCalorie.setUser(userOptional.get());
    } else {
      throw new RuntimeException("사용자를 찾을 수 없습니다: " + burnCalorie.getUser().getUserId());
    }

    return burnCalorieRepository.save(existingBurnCalorie);
  }

  @Transactional
  public void deleteBurnCalorie(int burnId) {
    BurnCalorie burnCalorie = burnCalorieRepository.findById(burnId)
        .orElseThrow(() -> new RuntimeException("섭취 칼로리를 찾을 수 없습니다: " + burnId));

    burnCalorie.setUser(null); // 관계 해제
    burnCalorieRepository.delete(burnCalorie);
  }
}
