package com.polytech.notes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polytech.notes.models.Matiere;
import com.polytech.notes.models.Note;
import com.polytech.notes.models.Unite;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{

	Note findNoteByEtudiantNumeroAndMatiereCode(String numero,String code); 
	Note findByEtudiantNomAndEtudiantPrenomAndMatiereCode(String nom,String prenom,String code); 
	Note findNoteByUniteCodeAndEtudiantNumero(String code,String numero);
	List<Note> findNoteByMatiereUniteCodeAndEtudiantNumero(String code,String numero);
	List<Note> findNoteByMatiereUniteCodeAndEtudiantNomAndEtudiantPrenom(String u,String nom,String prenom);
	Note findNoteByUniteCodeAndEtudiantNomAndEtudiantPrenom(String code,String nom,String prenom);
	//List<Note> findNoteByUnite
}