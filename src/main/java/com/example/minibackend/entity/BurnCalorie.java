package com.example.minibackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BurnCalorie {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int burnId;

  @Column(nullable = false)
  private LocalDate date;

  private int calorie;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name="user_id")
  private User user;

}
