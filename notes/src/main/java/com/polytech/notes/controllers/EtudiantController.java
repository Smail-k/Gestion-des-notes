package com.polytech.notes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polytech.notes.models.Etudiant;
import com.polytech.notes.models.Matiere;
import com.polytech.notes.models.Note;
import com.polytech.notes.models.Promotion;
import com.polytech.notes.services.EtudiantService;
import com.polytech.notes.services.NoteService;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

	@Autowired
	private EtudiantService etudiantService;
	@Autowired
	private NoteService noteService;
	
	@DeleteMapping("/delete")
	public String deleteEtudiant(String numero) {
		etudiantService.deleteEtudiantByNumero(numero);
		return "supprimé avec succés";
	}
	
	@PostMapping("/add")
	public String addEtudiant(String numero,String nom,String prenom,int annee) {
		Etudiant e=new Etudiant();
		e.setNom(nom);
		e.setPrenom(prenom);
		e.setNumero(numero);
		e.setPromotion(annee == 1 ? Promotion.Annee3 : annee == 2 ? Promotion.Annee4 : Promotion.Annee5);
		etudiantService.saveEtudiant(e);
		return "ajouté avec succés";
	}
	
	@PostMapping("/modify")
	public String modifyEtudiant(Long id,String numero,String nom,String prenom,int annee) {
		Etudiant e=new Etudiant();
		e.setId(id);
		e.setNom(nom);
		e.setPrenom(prenom);
		e.setNumero(numero);
		e.setPromotion(annee == 1 ? Promotion.Annee3 : annee == 2 ? Promotion.Annee4 : Promotion.Annee5);
		
		
		etudiantService.modifyEtudiant(e);
		return "modifié avec succés";
	}
	@GetMapping("/note/matiere")
	public Note getNoteByMatiere(String numero,Long id) {
		Matiere m = new Matiere();
		m.setId(id);
		return noteService.getNoteEtudiantByMatiere(numero,m);
	}
	@GetMapping("/note/matiereNom")
	public Note getNoteByMatiere(String nom,String prenom,Long id) {
		Matiere m = new Matiere();
		m.setId(id);
		return noteService.getNoteEtudiantByNomAndMatiere(nom,prenom,m);
	}
	@GetMapping("/note/nom")
	public Etudiant getNoteByEtudiant(String nom,String niveau) {
		return etudiantService.getNoteEtudiantByNom(nom, niveau);
	}
	
	@GetMapping("/note/niveau")
	public List<Note> getNoteByNiveau(String nom,String prenom,String niveau) {
		return etudiantService.getNoteAnnee(nom, prenom, niveau);
	}
	@GetMapping("/note/semestre")
	public List<Note> getNoteByEtudiant(String nom,String prenom,String sem) {
		return etudiantService.getNoteSemestre(nom, prenom, sem);
	}
	
}