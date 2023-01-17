package com.polytech.notes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polytech.notes.models.Matiere;
import com.polytech.notes.models.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{

	Note findNoteByEtudiantNumeroAndMatiere(String numero,Matiere m); 
	Note findByEtudiantNomAndEtudiantPrenomAndMatiere(String nom,String prenom,Matiere m); 
}