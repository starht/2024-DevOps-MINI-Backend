package com.example.minibackend.dto.FavoriteFood;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteFoodCreateDTO {
  private int id;
  private int userId;
  private int foodListId;
}
