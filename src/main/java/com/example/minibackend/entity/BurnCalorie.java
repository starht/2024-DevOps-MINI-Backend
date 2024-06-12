package com.example.minibackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BurnCalorie {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int burnId;

  @Column(nullable = false)
  private LocalDate date;

  private int calorie;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name="user_id")
  private User user  ;

  public BurnCalorie(int burnId, LocalDate date, int calorie, String userId) {
    this.burnId = burnId;
    this.date = date;
    this.calorie = calorie;
    this.user = new User();
    this.user.setUserId(userId);
  }
}
