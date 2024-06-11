package com.example.minibackend.service;

import com.example.minibackend.dto.RecommendExerciseDTO;
import com.example.minibackend.entity.RecommendExercise;
import com.example.minibackend.entity.RecommendFood;
import com.example.minibackend.repository.RecommendExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RecommendExerciseService {
    @Autowired
    private RecommendExerciseRepository recommendExerciseRepository;

    public List<RecommendExercise> getAllRecommendExercise() {
        return recommendExerciseRepository.findAll();
    }

    public RecommendExercise getReById(int exerciseListId) {
        return recommendExerciseRepository.findById(exerciseListId).get();
    }
    @Transactional
    public RecommendExercise updateRecommendExercise(RecommendExercise updatedRecommendExercise) {
        RecommendExercise existingRecommendExer = recommendExerciseRepository.findById(updatedRecommendExercise.getExerciseListId()).orElse(null);

        if (existingRecommendExer == null) {
            throw new RuntimeException("추천 운동을 찾을 수 없습니다: " + updatedRecommendExercise.getExerciseListId());
        }

        existingRecommendExer.setKcal(updatedRecommendExercise.getKcal());
        existingRecommendExer.setPicture(updatedRecommendExercise.getPicture());
        existingRecommendExer.setYoutubeId(updatedRecommendExercise.getYoutubeId());
        return recommendExerciseRepository.save(existingRecommendExer);
    }

    @Transactional
    public void delRecommendExercise(int exerciseListId) {
        recommendExerciseRepository.deleteById(exerciseListId);
    }

    @Transactional
    public RecommendExercise addRecommendExercise(RecommendExerciseDTO recommendExerciseDTO) {
        RecommendExercise recommendExercise = new RecommendExercise();
        recommendExercise.setExerciseName(recommendExerciseDTO.getExerciseName());
        recommendExercise.setKcal(recommendExerciseDTO.getKcal());
        recommendExercise.setPicture(recommendExerciseDTO.getPicture());
        recommendExercise.setYoutubeId(recommendExerciseDTO.getYoutubeId());
        return recommendExerciseRepository.save(recommendExercise);
    }


}
