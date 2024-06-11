package com.example.minibackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(length=20, unique=true, nullable=false)
  private String userId;

  @Column(length=20, nullable=false)
  private String password;

  @Column(length=10, nullable=false)
  private String name;

  private float bmi;

  public void setBmi(float bmi) {
    this.bmi = bmi;
  }
}
