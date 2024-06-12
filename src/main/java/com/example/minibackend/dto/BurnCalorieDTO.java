package com.example.minibackend.dto;

import com.example.minibackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BurnCalorieDTO {
    private int burnId;
    private LocalDate burnDate;
    private int calorie;
    private String userId;

    public BurnCalorieDTO(int burnId, LocalDate burnDate, int calorie, String userId) {
        this.burnId = burnId;
        this.burnDate = burnDate;
        this.calorie = calorie;
        this.userId = userId;
    }
}
