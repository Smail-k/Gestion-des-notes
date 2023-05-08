package com.polytech.notes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.polytech.notes.models.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, String>{

	@Query("select distinct p.promo from Promotion p")
	List<String> allPromotions();
	
	@Query("select promo from Promotion p where p.annee.annee=:annee")
	List<String> getPromotionsByAnnee(String annee);
}
