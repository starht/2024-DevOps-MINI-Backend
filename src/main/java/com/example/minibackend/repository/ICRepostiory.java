package com.example.minibackend.repository;

import com.example.minibackend.entity.IntakeCalorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICRepostiory extends JpaRepository<IntakeCalorie,Integer> {
}
