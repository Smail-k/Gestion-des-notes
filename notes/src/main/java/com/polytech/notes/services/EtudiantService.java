package com.polytech.notes.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.polytech.notes.models.Etudiant;
import com.polytech.notes.models.Note;
import com.polytech.notes.models.Promotion;

@Service
public interface EtudiantService {

	Etudiant saveEtudiant(Etudiant e);
	Etudiant modifyEtudiant(Etudiant e);
	void deleteEtudiantById(Etudiant e);
	void deleteEtudiantByNumero(String numero);
	Etudiant getEtudiantByNumero(String numero); 
	Etudiant getNoteEtudiantByNumero(String numero,String annee);
	Etudiant getNoteEtudiantByNom(String nom,String annee);
	List<Note> getNoteSemestre(String nom,String prenom,String sem);
	List<Note> getNoteAnnee(String nom,String prenom,String niveau);
	
	List<Etudiant> getEtudiants(Promotion p,String annee);
	List<String> getAnneeUniversitaires();
	List<String> getPromotions();
	List<Etudiant> getAll();
	String lastEtudiantNumero();
	List<Etudiant> getEtudiantsMoyenneModules(Promotion p, String annee);
	
}