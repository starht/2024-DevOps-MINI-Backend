package com.example.minibackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IntakeCalorie {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int intakeId;

  @Column(nullable = false)
  private LocalDate date;

  private int breakfast;

  private int lunch;

  private int dinner;

  private int snack;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name="user_id")
  private User user;

  public IntakeCalorie(LocalDate date, int breakfast, int lunch, int dinner, int snack, User user) {
    this.date = date;
    this.breakfast = breakfast;
    this.lunch = lunch;
    this.dinner = dinner;
    this.snack = snack;
    this.user = user;
  }
}
