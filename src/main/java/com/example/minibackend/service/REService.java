package com.example.minibackend.service;

import com.example.minibackend.entity.RecommendExercise;
import com.example.minibackend.repository.RERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class REService {
    @Autowired
    private RERepository reRepository;

    public List<RecommendExercise> getAllRecommendExercise() {
        return reRepository.findAll();
    }

    public RecommendExercise getReById(int exerciseListId) {
        return reRepository.findById(exerciseListId).get();
    }

    @Transactional
    public void RecommendExercise(int exerciseListId) {
        reRepository.deleteById(exerciseListId);
    }

    @Transactional
    public void addRecommendExercise(RecommendExercise re) {

    }


}
