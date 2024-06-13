package com.example.minibackend.dto.FavoriteFood;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteFoodDTO {
    private int id;
    private int userId;
    private String foodName;
    private float kcal;
    private String picture;
}
