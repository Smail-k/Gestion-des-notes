package com.polytech.notes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polytech.notes.models.Etudiant;
import com.polytech.notes.models.Matiere;
import com.polytech.notes.models.Note;
import com.polytech.notes.models.Promotion;
import com.polytech.notes.models.Unite;
import com.polytech.notes.services.EtudiantService;
import com.polytech.notes.services.NoteService;
import com.polytech.notes.services.UniteService;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

	@Autowired
	private EtudiantService etudiantService;
	@Autowired
	private NoteService noteService;
	@Autowired
	private UniteService uniteService;
	
	
	@DeleteMapping("/delete/{numero}")
	public String deleteEtudiant(@PathVariable String numero) {
		etudiantService.deleteEtudiantByNumero(numero);
		return "supprimé avec succés";
	}
	
	@PostMapping("/add")
	public String addEtudiant(@RequestBody Etudiant e) {
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
	
	@GetMapping("/get/{numero}")
	public Etudiant getEtudiant(@PathVariable String numero) {
		return etudiantService.getEtudiantByNumero(numero);
	}
	//chercher la note d'une matiere par numero d'etudiant
	@GetMapping("/note/matiere/numero/")
	public Note getNoteByMatiere(String num,String code) {
		return noteService.getNoteEtudiantByMatiere(num,code);
	}

	//http://localhost:8080/api/etudiants/note/matiere/nom/?nom=MQJQZ&prenom=EHC&code=JIN71F
	@GetMapping("/note/matiere/nom")
	public Note getNoteByMatiere(String nom,String prenom,String code) {
		return noteService.getNoteEtudiantByNomAndMatiere(nom,prenom,code);
	}
	
	@GetMapping("/note/nom")
	public Etudiant getNoteByEtudiant(String nom,String niveau) {
		return etudiantService.getNoteEtudiantByNom(nom, niveau); 
	}
	
	//http://localhost:8080/api/etudiants/note/niveau/?nom=MQJQZ&prenom=EHC&niveau=4A
	@GetMapping("/note/niveau")
	public List<Note> getNoteByNiveau(String nom,String prenom,String niveau) {
		return etudiantService.getNoteAnnee(nom, prenom, niveau);
	}
	
	//http://localhost:8080/api/etudiants/note/semestre/?nom=MQJQZ&prenom=EHC&sem=SEM 7
	@GetMapping("/note/semestre")
	public List<Note> getNoteBySemestre(String nom,String prenom,String sem) {
		return etudiantService.getNoteSemestre(nom, prenom, sem);
	}
	//http://localhost:8080/api/etudiants/note/unite/?nom=MQJQZ&prenom=EHC&codeUnite=JIN7U1B
	@GetMapping("/note/unite")
	public List<Note> getNoteOfModule(String nom,String prenom,String codeUnite) {
		Unite u = uniteService.findUniteByCode(codeUnite);
//		System.out.println(u);
		List<Note> list= noteService.getNoteByUnite(codeUnite, nom, prenom);
		list.add(noteService.getTotalNoteUnite(codeUnite,nom, prenom));
		return list;
	}
	
	//http://localhost:8080/api/etudiants/liste/?promo=4A&annee=2021/2022
	@GetMapping("/liste")
	public List<Etudiant> etudiants(String promo, String annee) {
		if(promo.equals("3A"))
			return etudiantService.getEtudiants(Promotion.Annee3, annee);
		if(promo.equals("4A"))
			return etudiantService.getEtudiants(Promotion.Annee4, annee);
		if(promo.equals("5A"))
			return etudiantService.getEtudiants(Promotion.Annee5, annee);
		return null;
	}
	
	//http://localhost:8080/api/etudiants/all/
	@GetMapping("/all")
	public List<Etudiant> etudiants() {
		return etudiantService.getAll();
	}
	
	@GetMapping("/lastNumero")
	public String lastEtudiant() {
		return etudiantService.lastEtudiantNumero();
	}
	
	
}