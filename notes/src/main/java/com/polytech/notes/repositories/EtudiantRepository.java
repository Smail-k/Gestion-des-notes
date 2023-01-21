package com.polytech.notes.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.polytech.notes.models.Etudiant;
import com.polytech.notes.models.Promotion;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long>{

	@Transactional
	@Modifying
	void deleteEtudiantByNumero(String numero);
	Etudiant getEtudiantByNumero(String numero);
	Etudiant findEtudiantByNumeroAndNotesAnnee(String numero,String annee);
	Etudiant findEtudiantByNomAndNotesAnnee(String nom,String annee);
	Etudiant getEtudiantByNomAndPrenom(String nom,String prenom);
	List<Etudiant> findEtudiantsByPromotionAndAnnee(Promotion p,Long annee);
	
	@Query("select distinct annee from Etudiant")
	List<String> findAnneeUniversitaires();
	
	@Query("select distinct promotion from Etudiant")
	List<String> findPromotions();
}