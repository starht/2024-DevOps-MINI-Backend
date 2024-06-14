package com.example.minibackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Calorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    private int monthUnit;

    private int goalKg;

    private float bmr;

    private float amr;

    private float tdee;

    private float eatNeeded;

    private float workoutNeeded;

    public Calorie(int id, String userId, int monthUnit, int goalKg, float bmr, float amr, float tdee, float eatNeeded, float workoutNeeded) {
        this.id = id;
        this.user = new User();
        this.user.setUserId(userId);
        this.monthUnit = monthUnit;
        this.goalKg = goalKg;
        this.bmr = bmr;
        this.amr = amr;
        this.tdee = tdee;
        this.eatNeeded = eatNeeded;
        this.workoutNeeded = workoutNeeded;
    }
}
