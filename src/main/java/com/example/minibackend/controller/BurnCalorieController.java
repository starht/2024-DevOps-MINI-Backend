package com.example.minibackend.controller;

import com.example.minibackend.dto.BurnCalorieDTO;
import com.example.minibackend.entity.BurnCalorie;
import com.example.minibackend.service.BurnCalorieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/burn")
@RequiredArgsConstructor
@Slf4j
public class BurnCalorieController {
    private final BurnCalorieService burnCalorieService;

    @GetMapping("/{userId}")
    public BurnCalorieDTO getBurnCalorieById(@PathVariable("userId")String userId) {
        return burnCalorieService.findByUserId(userId);
    }

    @PostMapping("/add")
    public BurnCalorieDTO addBurnCalorie(@RequestBody BurnCalorieDTO burnCalorieDTO) {
        BurnCalorie burnCalorie = burnCalorieService.addBurnCalorie(burnCalorieDTO);
        return burnCalorieService.convertToDTO(burnCalorie);
    }

    @PutMapping("/update")
    public BurnCalorieDTO updateBurnCalorie(@RequestParam("userId")String userId,@RequestBody BurnCalorieDTO burnCalorieDTO) {
        BurnCalorieDTO burnCalorie = new BurnCalorieDTO(
                burnCalorieDTO.getBurnId(), LocalDate.now(), burnCalorieDTO.getCalorie(), userId
        );
        log.info("updateBurnCalorie: {}", burnCalorie);
        BurnCalorie burnCalorie1 = burnCalorieService.updateBurnCalorie(burnCalorie);
        return burnCalorieService.convertToDTO(burnCalorie1);

    }

    @DeleteMapping("/delete/{burnId}")
    public void deleteBurnCalorie(@PathVariable("burnId")int burnId) {
        burnCalorieService.deleteBurnCalorie(burnId);
    }
}
