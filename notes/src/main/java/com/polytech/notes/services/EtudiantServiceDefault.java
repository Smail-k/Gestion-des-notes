package com.polytech.notes.services;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polytech.notes.models.Etudiant;
import com.polytech.notes.models.Note;
import com.polytech.notes.repositories.EtudiantRepository;

@Service
public class EtudiantServiceDefault implements EtudiantService{

	@Autowired
	private EtudiantRepository repository;
	
	@Override
	public Etudiant saveEtudiant(Etudiant e) {
		return repository.save(e);
	}

	@Override
	public Etudiant modifyEtudiant(Etudiant e) {
		//id doesn't change (fixed)
		Etudiant existingEtudiant= repository.findById(e.getId()).orElse(null);
		if(existingEtudiant==null)
			return null;
		existingEtudiant.setNom(e.getNom());
		existingEtudiant.setNotes(e.getNotes());
		existingEtudiant.setNumero(e.getNumero());
		existingEtudiant.setPrenom(e.getPrenom());
		existingEtudiant.setPromotion(e.getPromotion());
		repository.save(existingEtudiant);
		return e;
	}

	public void deleteEtudiantById(Etudiant e) {
		repository.deleteById(e.getId());
	}
	
	public void deleteEtudiantByNumero(String numero) {
		repository.deleteEtudiantByNumero(numero);
	}

	@Override
	public Etudiant getEtudiantByNumero(String numero) {
		return repository.getEtudiantByNumero(numero);
	}

	@Override
	public Etudiant getNoteEtudiantByNumero(String numero, String annee) {
		return repository.findEtudiantByNumeroAndNotesAnnee(numero, annee);
	}

	@Override
	public Etudiant getNoteEtudiantByNom(String nom, String annee) {
		return repository.findEtudiantByNomAndNotesAnnee(nom, annee);
	}
	
	public List<Note> getNoteAnnee(String nom,String prenom,String niveau) { //niveau : 3A,4A,5A
		List<Note> notes;
		switch (niveau) {
		case "3A":
			notes= getNoteSemestre(nom,prenom,"SEM 5");
			notes.addAll(getNoteSemestre(nom,prenom,"SEM 6"));
			return notes;
		case "4A":
			notes= getNoteSemestre(nom,prenom,"SEM 7");
			notes.addAll(getNoteSemestre(nom,prenom,"SEM 8"));
			return notes;
		case "5A":
			notes= getNoteSemestre(nom,prenom,"SEM 9");
			notes.addAll(getNoteSemestre(nom,prenom,"SEM 10"));
			return notes;
		default:
			return null;
		}
	}
	
	public List<Note> getNoteSemestre(String nom,String prenom,String sem) {
		Double noteFinale=0.0;
		Double totalCoefficient=1.0;
		Etudiant e= repository.getEtudiantByNomAndPrenom(nom, prenom);
		List<Note> notes = e.getNotes();
		if(notes.size()==0)
			return null;
		List<Note> notesSemestre = new Vector<Note>();
		for (Note note : notes) {
			if(note.getUnite().getSemestre().getNom().equals(sem)) {
				noteFinale+= note.getNote()*note.getMatiere().getCoefficient();
				notesSemestre.add(note);
				if(totalCoefficient==1.0)
					totalCoefficient=note.getUnite().getSemestre().getSemestreCoefficient();
				
			}
		}
		
		Note n = new Note(); 
		n.setNote(noteFinale/totalCoefficient);
		notesSemestre.add(n);
		return notesSemestre;
	}
}