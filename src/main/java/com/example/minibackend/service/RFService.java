package com.example.minibackend.service;

import com.example.minibackend.entity.RecommendFood;
import com.example.minibackend.repository.RFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RFService {
    @Autowired
    private RFRepository rfRepository;

    public List<RecommendFood> getAllRecommendFood() {
        return rfRepository.findAll();
    }

    public RecommendFood getRfById(int foodListId) {
        return rfRepository.findById(foodListId).get();
    }

    @Transactional
    public void deleteRfById(int foodListId) {
        rfRepository.deleteById(foodListId);
    }

    @Transactional
    public RecommendFood addRf(RecommendFood recommendFood) {
        return rfRepository.save(recommendFood);
    }
}
