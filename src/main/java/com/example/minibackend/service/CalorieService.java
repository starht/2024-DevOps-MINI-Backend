package com.example.minibackend.service;

import com.example.minibackend.dto.Calorie.CalorieDTO;
import com.example.minibackend.entity.Calorie;
import com.example.minibackend.entity.User;
import com.example.minibackend.repository.CalorieRepository;
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
public class CalorieService {
  private final CalorieRepository calorieRepository;
  private final UserRepository userRepository;

  public List<Calorie> getAllCalories() {
    List<Calorie> calories = calorieRepository.findAll();
    for (Calorie calorie : calories) {
      Hibernate.initialize(calorie.getUser());
    }
    return calories;
  }

  public CalorieDTO getCalorieByUser(String userId) {
    Optional<User> userOptional = userRepository.findByUserId(userId);
    if (userOptional.isPresent()) {
      Calorie calorie = calorieRepository.findByUser(userOptional.get());
      Hibernate.initialize(calorie.getUser());
      return new CalorieDTO(calorie.getId(), calorie.getUser().getUserId(), calorie.getMonthUnit(), calorie.getGoalKg(), calorie.getBmr(), calorie.getAmr(), calorie.getTdee(), calorie.getEatNeeded(), calorie.getWorkoutNeeded());
    } else {
      throw new RuntimeException("사용자를 찾을 수 없습니다: " + userId);
    }
  }

  public Optional<Calorie> findById(int calorieId) {
    Optional<Calorie> calorie = calorieRepository.findById(calorieId);
    if (calorie.isPresent()) {
      Hibernate.initialize(calorie.get().getUser());
    }
    return calorie;
  }

  @Transactional
  public Calorie addCalorie(Calorie calorie) {
    Optional<User> userOptional = userRepository.findByUserId(calorie.getUser().getUserId());
    if (userOptional.isPresent()) {
      calorie.setUser(userOptional.get());
      calorie.setMonthUnit(calorie.getMonthUnit());
      calorie.setGoalKg(calorie.getGoalKg());
      calorie.setBmr(calorie.getBmr());
      calorie.setAmr(calorie.getAmr());
      calorie.setTdee(calorie.getTdee());
      calorie.setEatNeeded(calorie.getEatNeeded());
      calorie.setWorkoutNeeded(calorie.getWorkoutNeeded());
      return calorieRepository.save(calorie);
    } else {
      throw new RuntimeException("사용자를 찾을 수 없습니다: " + calorie.getUser().getUserId());
    }
  }

  @Transactional
  public Calorie updateCalorie(Calorie calorie){
    Calorie existingCalorie = calorieRepository.findById(calorie.getId()).orElse(null);

    if (existingCalorie == null) {
      throw new RuntimeException("칼로리 정보를 찾을 수 없습니다: " + calorie.getId());
    }

    existingCalorie.setMonthUnit(calorie.getMonthUnit());
    existingCalorie.setGoalKg(calorie.getGoalKg());
    existingCalorie.setBmr(calorie.getBmr());
    existingCalorie.setAmr(calorie.getAmr());
    existingCalorie.setTdee(calorie.getTdee());
    existingCalorie.setEatNeeded(calorie.getEatNeeded());
    existingCalorie.setWorkoutNeeded(calorie.getWorkoutNeeded());

    Optional<User> userOptional = userRepository.findByUserId(calorie.getUser().getUserId());
    if (userOptional.isPresent()) {
      existingCalorie.setUser(userOptional.get());
    } else {
      throw new RuntimeException("사용자를 찾을 수 없습니다: " + calorie.getUser().getUserId());
    }

    return calorieRepository.save(existingCalorie);
  }

  @Transactional
  public void deleteCalorieById(int calorieId) {
    Calorie calorie = calorieRepository.findById(calorieId)
        .orElseThrow(() -> new RuntimeException("칼로리 정보를 찾을 수 없습니다: " + calorieId));

    calorie.setUser(null); // 관계 해제
    calorieRepository.delete(calorie);
  }

}
