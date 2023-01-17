package com.polytech.notes.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.polytech.notes.models.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long>{

	@Transactional
	@Modifying
	void deleteEtudiantByNumero(String numero);
	Etudiant getEtudiantByNumero(String numero);
	Etudiant findEtudiantByNumeroAndNotesAnnee(String numero,String annee);
	Etudiant findEtudiantByNomAndNotesAnnee(String nom,String annee);
	Etudiant getEtudiantByNomAndPrenom(String nom,String prenom);
	
}