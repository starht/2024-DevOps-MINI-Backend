package com.example.minibackend.controller;

import com.example.minibackend.dto.BurnCalorieDTO;
import com.example.minibackend.entity.BurnCalorie;
import com.example.minibackend.service.BurnCalorieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/BurnCalorie")
@RequiredArgsConstructor
public class BurnCalorieController {
    private final BurnCalorieService burnCalorieService;

    @GetMapping("/{userId}")
    public BurnCalorieDTO getBurnCalorieById(@PathVariable("userId")String userId) {
        return burnCalorieService.findByUserId(userId);
    }

    @PostMapping("/add")
    public BurnCalorie addBurnCalorie(@RequestBody BurnCalorieDTO burnCalorieDTO) {
        return burnCalorieService.addBurnCalorie(burnCalorieDTO);
    }

    @DeleteMapping("/delete/{burnId}")
    public void deleteBurnCalorie(@PathVariable("burnId")int burnId) {
        burnCalorieService.deleteBurnCalorie(burnId);
    }
}
