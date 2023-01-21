package com.polytech.notes.services;

import org.springframework.stereotype.Service;

import com.polytech.notes.models.Matiere;
import com.polytech.notes.models.Note;

@Service
public interface NoteService {

	Note getNoteEtudiantByMatiere(String numero,Matiere matiere);
	Note getNoteEtudiantByNomAndMatiere(String nom,String prenom,Matiere matiere);
	Note saveNote(Note note);
}
