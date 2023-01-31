package com.polytech.notes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polytech.notes.models.Unite;

@Repository
public interface UniteRepository extends JpaRepository<Unite, Long>{
	
	Unite findUniteBylibelle(String libelle);
	Unite findUniteByCode(String code);
	List<Unite> findUniteBySemestreNom(String nom);
	
}
