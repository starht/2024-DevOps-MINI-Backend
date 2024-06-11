package com.example.minibackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
  private int id;
  private String userId;
  private String password;
  private String name;
  private float bmi;
}
