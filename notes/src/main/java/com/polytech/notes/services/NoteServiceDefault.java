package com.polytech.notes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polytech.notes.models.Matiere;
import com.polytech.notes.models.Note;
import com.polytech.notes.models.Unite;
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
		Note n = getNoteEtudiantByMatiere(note.getEtudiant().getNumero(), note.getMatiere());
		if(n!=null) {
			n.setNote(note.getNote());
			return noteRepository.save(n);
		}
		return noteRepository.save(note);
	}

	@Override
	public Note getNoteByUniteCodeAndEtudiantNumero(String code, String numero) {
		return noteRepository.findNoteByUniteCodeAndEtudiantNumero(code, numero);
	}
	
	@Override
	public List<Note> getNoteByMatiereUnite(String code,String numero) {
		return noteRepository.findNoteByMatiereUniteCodeAndEtudiantNumero(code,numero);
	}
	
	@Override
	public List<Note> getNoteByUnite(String unite,String nom,String prenom) {
		return noteRepository.findNoteByMatiereUniteCodeAndEtudiantNomAndEtudiantPrenom(unite, nom, prenom);
	}
	@Override
	public Note getTotalNoteUnite(String unite,String nom,String prenom) {
		return noteRepository.findNoteByUniteCodeAndEtudiantNomAndEtudiantPrenom(unite,nom, prenom);
	}
}