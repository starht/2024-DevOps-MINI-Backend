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
    return calorieRepository.findAll();
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

  @Transactional
  public Calorie addCalorie(Calorie calorieDTO) {
    Optional<User> userOptional = userRepository.findByUserId(calorieDTO.getUser().getUserId());
    if (userOptional.isPresent()) {
      Calorie calorie = new Calorie(calorieDTO.getId(), userOptional.get(), calorieDTO.getMonthUnit(), calorieDTO.getGoalKg(), calorieDTO.getBmr(), calorieDTO.getAmr(), calorieDTO.getTdee(), calorieDTO.getEatNeeded(), calorieDTO.getWorkoutNeeded());
      return calorieRepository.save(calorie);
    } else {
      throw new RuntimeException("사용자를 찾을 수 없습니다: " + calorieDTO.getUser().getUserId());
    }
  }

  @Transactional
  public Calorie updateCalorie(Calorie calorieDTO) {
    Optional<User> userOptional = userRepository.findByUserId(calorieDTO.getUser().getUserId());
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      Optional<Calorie> existingCalorieOptional = calorieRepository.findById(calorieDTO.getId());
      if (existingCalorieOptional.isPresent()) {
        Calorie existingCalorie = existingCalorieOptional.get();
        existingCalorie.setUser(user);
        existingCalorie.setMonthUnit(calorieDTO.getMonthUnit());
        existingCalorie.setGoalKg(calorieDTO.getGoalKg());
        existingCalorie.setBmr(calorieDTO.getBmr());
        existingCalorie.setAmr(calorieDTO.getAmr());
        existingCalorie.setTdee(calorieDTO.getTdee());
        existingCalorie.setEatNeeded(calorieDTO.getEatNeeded());
        existingCalorie.setWorkoutNeeded(calorieDTO.getWorkoutNeeded());
        return calorieRepository.save(existingCalorie);
      } else {
        throw new RuntimeException("칼로리 정보를 찾을 수 없습니다: " + calorieDTO.getId());
      }
    } else {
      throw new RuntimeException("사용자를 찾을 수 없습니다: " + calorieDTO.getUser().getUserId());
    }
  }

  @Transactional
  public void deleteCalorieById(int calorieId) {
    Calorie calorie = calorieRepository.findById(calorieId)
        .orElseThrow(() -> new RuntimeException("칼로리 정보를 찾을 수 없습니다: " + calorieId));

    calorie.setUser(null);
    calorieRepository.delete(calorie);
  }

}
