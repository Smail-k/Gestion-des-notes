package com.polytech.notes.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.polytech.notes.models.Etudiant;
import com.polytech.notes.models.Promotion;
import com.polytech.notes.models.PromotionType;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long>{

	@Transactional
	@Modifying
	void deleteEtudiantByNumero(String numero);
	Etudiant getEtudiantByNumero(String numero);
	Etudiant findEtudiantByNumeroAndNotesAnnee(String numero,String annee);
	Etudiant findEtudiantByNomAndNotesAnnee(String nom,String annee);
	Etudiant getEtudiantByNomAndPrenom(String nom,String prenom);
	List<Etudiant> findEtudiantsByPromotionPromoAndAnnee(String p,String annee);
	//List<Etudiant> findEtudiantsByPromotionAndAnnee(Promotion p,String annee);
	List<Etudiant> findEtudiantByPromotionPromoAndNotesAnneeAndNotesSituation(String promo,String annee,boolean situation);
	
	@Query("select distinct annee from Etudiant")
	List<String> findAnneeUniversitaires();
	
	@Query("select distinct promotion from Etudiant")
	List<String> findPromotions();
	
	@Query("select max(numero) from Etudiant")
	String findLastNumero();
	
	@Query("SELECT e.numero,e.nom,e.prenom FROM Etudiant e JOIN e.notes n WHERE n.situation=0 AND n.annee = :annee AND e.promotion=:promo AND n.session='normale' GROUP BY e")
	List<Object[]> etudiantsRattrapages(Promotion promo,String annee);

}