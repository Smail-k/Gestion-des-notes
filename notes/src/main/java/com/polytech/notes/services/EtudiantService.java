package com.polytech.notes.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.polytech.notes.models.Etudiant;
import com.polytech.notes.models.Note;

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
}