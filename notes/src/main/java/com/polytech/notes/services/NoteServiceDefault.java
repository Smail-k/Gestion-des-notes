package com.polytech.notes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polytech.notes.models.Matiere;
import com.polytech.notes.models.Note;
import com.polytech.notes.repositories.NoteRepository;

@Service
public class NoteServiceDefault implements NoteService{

	@Autowired
	private NoteRepository noteRepository;
	
	public Note getNoteEtudiantByMatiere(String numero,Matiere m) {
		return noteRepository.findNoteByEtudiantNumeroAndMatiere(numero, m);
	}

	@Override
	public Note getNoteEtudiantByNomAndMatiere(String nom, String prenom, Matiere m) {
		return noteRepository.findByEtudiantNomAndEtudiantPrenomAndMatiere(nom, prenom, m);
	}

	@Override
	public Note saveNote(Note note) {
		return noteRepository.save(note);
	}

	
}