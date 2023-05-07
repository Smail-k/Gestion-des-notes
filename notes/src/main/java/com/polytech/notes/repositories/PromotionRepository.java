package com.polytech.notes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polytech.notes.models.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, String>{

	
}
