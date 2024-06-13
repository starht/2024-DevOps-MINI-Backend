package com.example.minibackend.service;

import com.example.minibackend.entity.IntakeCalorie;
import com.example.minibackend.entity.User;
import com.example.minibackend.repository.IntakeCalorieRepository;
import com.example.minibackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class IntakeCalorieService {
  private final IntakeCalorieRepository intakeCalorieRepository;
  private final UserRepository userRepository;

  public List<IntakeCalorie> getAllIntakeCalories() {
    List<IntakeCalorie> intakeCalories = intakeCalorieRepository.findAll();
    for (IntakeCalorie intakeCalorie : intakeCalories) {
      Hibernate.initialize(intakeCalorie.getUser());
    }
    return intakeCalories;
  }

  public List<IntakeCalorie> getIntakeCalorieByUser(String userId) {
    Optional<User> userOpt = userRepository.findByUserId(userId);
    if (userOpt.isPresent()) {
      User user = userOpt.get();
      List<IntakeCalorie> intakeCalories = intakeCalorieRepository.findAllByUser(user);
      for (IntakeCalorie intakeCalorie : intakeCalories) {
        Hibernate.initialize(intakeCalorie.getUser());
      }
      return intakeCalories;
    }
    throw new RuntimeException("사용자를 찾을 수 없습니다: " + userId);
  }

  @Transactional
  public IntakeCalorie addIntakeCalorie(IntakeCalorie intakeCalorie) {
    Optional<User> userOptional = userRepository.findByUserId(intakeCalorie.getUser().getUserId());
    if (userOptional.isPresent()) {
      intakeCalorie.setUser(userOptional.get());
      intakeCalorie.setBreakfast(intakeCalorie.getBreakfast());
      intakeCalorie.setLunch(intakeCalorie.getLunch());
      intakeCalorie.setDinner(intakeCalorie.getDinner());
      intakeCalorie.setSnack(intakeCalorie.getSnack());
      return intakeCalorieRepository.save(intakeCalorie);
    }
    throw new RuntimeException("사용자를 찾을 수 없습니다: " + intakeCalorie.getUser().getUserId());
  }

  @Transactional
  public IntakeCalorie updateIntakeCalorie(IntakeCalorie intakeCalorie) {
    IntakeCalorie existingIntakeCalorie = intakeCalorieRepository.findById(intakeCalorie.getIntakeId()).orElse(null);

    if (existingIntakeCalorie == null) {
      throw new RuntimeException("섭취 칼로리를 찾을 수 없습니다: " + intakeCalorie.getIntakeId());
    }

    existingIntakeCalorie.setBreakfast(intakeCalorie.getBreakfast());
    existingIntakeCalorie.setLunch(intakeCalorie.getLunch());
    existingIntakeCalorie.setDinner(intakeCalorie.getDinner());
    existingIntakeCalorie.setSnack(intakeCalorie.getSnack());

    Optional<User> userOpt = userRepository.findByUserId(intakeCalorie.getUser().getUserId());
    if (userOpt.isPresent()) {
      existingIntakeCalorie.setUser(userOpt.get());
    } else {
      throw new RuntimeException("사용자를 찾을 수 없습니다: " + intakeCalorie.getUser().getUserId());
    }

    return intakeCalorieRepository.save(existingIntakeCalorie);
  }

  @Transactional
  public void deleteIntakeCalorieById(int intakeId) {
    IntakeCalorie intakeCalorie = intakeCalorieRepository.findById(intakeId)
        .orElseThrow(() -> new RuntimeException("섭취 칼로리를 찾을 수 없습니다: " + intakeId));

    intakeCalorie.setUser(null);  // 관계 해제
    intakeCalorieRepository.delete(intakeCalorie);
  }
}
