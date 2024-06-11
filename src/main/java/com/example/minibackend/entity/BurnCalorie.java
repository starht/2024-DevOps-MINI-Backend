package com.example.minibackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BurnCalorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int burnId;

    private LocalDate date;

    private float calorie;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
