package com.example.minibackend.service;

import com.example.minibackend.dto.BurnCalorieDTO;
import com.example.minibackend.dto.UserDTO;
import com.example.minibackend.entity.BurnCalorie;
import com.example.minibackend.entity.User;
import com.example.minibackend.repository.BurnCalorieRepository;
import com.example.minibackend.repository.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
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

//    @Transactional
//    public BurnCalorie updateBurnCalorie(BurnCalorieDTO burnCalorieDTO) {
//        Optional<User> byUserId = userRepository.findByUserId(burnCalorieDTO.getUserId());
//    }
    @Transactional
    public void deleteBurnCalorie(int id) {
        burnCalorieRepository.deleteById(id);
    }
}
