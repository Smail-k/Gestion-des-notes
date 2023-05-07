package com.polytech.notes.services;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polytech.notes.models.Etudiant;
import com.polytech.notes.models.Note;
import com.polytech.notes.models.Promotion;
import com.polytech.notes.models.PromotionType;
import com.polytech.notes.repositories.EtudiantRepository;

@Service
public class EtudiantServiceDefault implements EtudiantService{

	@Autowired
	private EtudiantRepository repository;
	
	@Override
	public Etudiant saveEtudiant(Etudiant e) {
		if(repository.getEtudiantByNumero(e.getNumero())==null)
			return repository.save(e);
		return null;
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
		Note n;
		switch (niveau) {
		case "3A":
			notes= getNoteSemestre(nom,prenom,"SEM 5");
			n = notes.get(notes.size()-1);
			notes.remove(notes.size()-1);
			notes.addAll(getNoteSemestre(nom,prenom,"SEM 6"));
			notes.add(notes.size()-1, n);
			break;
		case "4A":
			notes= getNoteSemestre(nom,prenom,"SEM 7");
			n = notes.get(notes.size()-1);
			notes.remove(notes.size()-1);
			notes.addAll(getNoteSemestre(nom,prenom,"SEM 8"));
			notes.add(notes.size()-1, n);
			break;
		case "5A":
			notes= getNoteSemestre(nom,prenom,"SEM 9");
			n = notes.get(notes.size()-1);
			notes.remove(notes.size()-1);
			notes.addAll(getNoteSemestre(nom,prenom,"SEM 10"));
			notes.add(notes.size()-1, n);
			break;
		default:
			return null;
		}
		Note finale = new Note();
		finale.setNote((notes.get(notes.size()-2).getNote()+notes.get(notes.size()-1).getNote())/2);
		notes.add(finale);
		return notes;
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
			if(note.getUnite()!=null && note.getUnite().getSemestre().getNom().equals(sem)) {
				noteFinale+= note.getNote()*note.getUnite().getCoefficient();
				notesSemestre.add(note);
				if(totalCoefficient==1.0)
					totalCoefficient=note.getUnite().getSemestre().getSemestreCoefficient();
			}else if(note.getUnite()==null && note.getMatiere().getUnite().getSemestre().getNom().equals(sem)) {
				notesSemestre.add(note);
				
			}
		}
		
		Note n = new Note(); 
		n.setNote(noteFinale/totalCoefficient);
		//System.out.println(totalCoefficient);
		notesSemestre.add(n);
		return notesSemestre;
	}

	@Override
	public List<Etudiant> getEtudiants(String p, String annee) {
		List<Etudiant> etudiants; 
		if(p.toLowerCase().equals("3a"))
		{
			etudiants=repository.findEtudiantsByPromotionPromoAndAnnee("3afise", annee);
			etudiants.addAll(repository.findEtudiantsByPromotionPromoAndAnnee("3afisa", annee));
			return etudiants;
		}
		else if(p.toLowerCase().equals("4a"))
		{
			etudiants=repository.findEtudiantsByPromotionPromoAndAnnee("4afise", annee);
			etudiants.addAll(repository.findEtudiantsByPromotionPromoAndAnnee("4afisa", annee));
			return etudiants;
		}
		else if(p.toLowerCase().equals("5a"))
		{
			etudiants=repository.findEtudiantsByPromotionPromoAndAnnee("5afise", annee);
			etudiants.addAll(repository.findEtudiantsByPromotionPromoAndAnnee("5afisa", annee));
			return etudiants;
		}
		
		
		return repository.findEtudiantsByPromotionPromoAndAnnee(p, annee);
	}
	
	@Override
	public List<String> getAnneeUniversitaires() {
		return repository.findAnneeUniversitaires();
	}
	
	@Override
	public List<String> getPromotions() {
		return repository.findPromotions();
	}

	@Override
	public List<Etudiant> getAll() {
		return repository.findAll();
	}
	
	@Override
	public String lastEtudiantNumero() {
		return Integer.parseInt(repository.findLastNumero())+1+"";
	}
	@Override
	public List<Etudiant> getEtudiantsMoyenneModules(String p, String annee) {
		List<Etudiant> etudiants = repository.findEtudiantsByPromotionPromoAndAnnee(p, annee);
		List<Note> list ;
		for (Etudiant etudiant : etudiants) {
			list = new Vector<Note>();
			for (Note n : etudiant.getNotes()) {
				if(n.getMatiere()==null) {
					//etudiant.getNotes().remove(n);
					list.add(n);
				}
			}
			etudiant.setNotes(list);
		}
		return etudiants;
	}

	@Override
	public List<Object[]> getEtudiantRattrapage(String promo,String annee) {
		Promotion p = new Promotion(promo);
		return repository.etudiantsRattrapages(p, annee);
	}
}