package com.example.minibackend.service;

import com.example.minibackend.dto.BurnCalorieDTO;
import com.example.minibackend.entity.BurnCalorie;
import com.example.minibackend.entity.User;
import com.example.minibackend.repository.BurnCalorieRepository;
import com.example.minibackend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@Slf4j
public class BurnCalorieService {
    @Autowired
    private BurnCalorieRepository burnCalorieRepository;
    @Autowired
    private UserRepository userRepository;

    public BurnCalorieDTO findByUserId(String userId) {
        Optional<User> byUserId = userRepository.findByUserId(userId);
        if (byUserId.isPresent()) {
            BurnCalorie burnCalorie = burnCalorieRepository.findByUser(byUserId.get());
            Hibernate.initialize(burnCalorie.getUser());
            return new BurnCalorieDTO(
                    burnCalorie.getBurnId(),
                    burnCalorie.getDate(),
                    burnCalorie.getCalorie(),
                    burnCalorie.getUser().getUserId()
            );
        }else {
            throw new RuntimeException("사용자를 찾을 수 없습니다: " + userId);
        }
    }

    @Transactional
    public BurnCalorie addBurnCalorie(BurnCalorieDTO burnCalorieDTO) {
        Optional<User> byUserId = userRepository.findByUserId(burnCalorieDTO.getUserId());
        if (byUserId.isPresent()) {
            BurnCalorie burnCalorie = new BurnCalorie();
            burnCalorie.setBurnId(burnCalorieDTO.getBurnId());
            burnCalorie.setDate(LocalDate.now());
            burnCalorie.setCalorie(burnCalorieDTO.getCalorie());
            burnCalorie.setUser(byUserId.get());
            return burnCalorieRepository.save(burnCalorie);
        }else {
            throw new RuntimeException("Cannot add: " + burnCalorieDTO.getUserId());
        }
    }

    public BurnCalorieDTO convertToDTO(BurnCalorie burnCalorie) {
        return new BurnCalorieDTO(
                burnCalorie.getBurnId(),
                burnCalorie.getDate(),
                burnCalorie.getCalorie(),
                burnCalorie.getUser().getUserId()
        );
    }

    @Transactional
    public BurnCalorie updateBurnCalorie(BurnCalorieDTO burnCalorieDTO) {
        List<User> users = userRepository.findAllByUserId(burnCalorieDTO.getUserId());
        if (users.isEmpty()) {
            throw new RuntimeException("No users found with Id: " + burnCalorieDTO.getUserId());
        }
        BurnCalorie updatedBurnCalorie = null;
        for (User user : users) {
            List<BurnCalorie> burnCalories = burnCalorieRepository.findAllByUser(user);
            for (BurnCalorie existingBurnCalorie : burnCalories) {
                if (existingBurnCalorie.getDate().isEqual(burnCalorieDTO.getBurnDate())) {
                    existingBurnCalorie.setCalorie(burnCalorieDTO.getCalorie());
                    updatedBurnCalorie = burnCalorieRepository.save(existingBurnCalorie);
                    break;
                }
            }
            if (updatedBurnCalorie != null) {
                break;
            }
        }
        if (updatedBurnCalorie == null) {
            throw new RuntimeException("No matching Date found for user Id: "
                    + burnCalorieDTO.getUserId() + " on date: " + burnCalorieDTO.getBurnDate());
        }
        return new BurnCalorie(
                updatedBurnCalorie.getBurnId(),
                updatedBurnCalorie.getDate(),
                updatedBurnCalorie.getCalorie(),
                updatedBurnCalorie.getUser().getUserId()
        );
    }


    @Transactional
    public void deleteBurnCalorie(int id) {
        burnCalorieRepository.deleteById(id);
    }
}
