package com.example.minibackend.dto.RecommendFood;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendFoodDTO {
  private int foodListId;
  private String foodName;
  private float kcal;
  private String picture;
}
