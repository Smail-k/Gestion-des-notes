package com.polytech.notes.controllers;


import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.polytech.notes.models.Etudiant;
import com.polytech.notes.models.Matiere;
import com.polytech.notes.models.Note;
import com.polytech.notes.models.Semestre;
import com.polytech.notes.models.Unite;
import com.polytech.notes.parsers.ExcelParser;
import com.polytech.notes.services.EtudiantService;
import com.polytech.notes.services.MatiereService;
import com.polytech.notes.services.NoteService;
import com.polytech.notes.services.SemestreService;
import com.polytech.notes.services.UniteService;

@RestController
@RequestMapping("/api")
public class ImportController {

	@Autowired 
	private SemestreService semestreService;
	@Autowired
	private UniteService uniteService;
	@Autowired
	private MatiereService matiereService;
	@Autowired
	private EtudiantService etudiantService;
	@Autowired
	private NoteService noteService;
	
	
	@PostMapping("/excel")
	public String excelReader(@RequestParam("file") MultipartFile excel) {
		
		ExcelParser parser = new ExcelParser();
		parser.excelReader(excel);
		List<Semestre> semestres = parser.getSemestres();
		for (Semestre semestre : semestres) {
			semestreService.saveSemestre(semestre);
		}
		List<Unite> unites = parser.getUnites();
		for (Unite unite : unites) {
			uniteService.saveModule(unite);
		}
		List<Matiere> matieres = parser.getMatieres();
		for (Matiere matiere : matieres) {
			matiereService.saveMatiere(matiere);
		}
	
		
		return "importation avec succée";
		
	}
	
	@PostMapping("/excel/etudiant")
	public String importEtudiants(@RequestParam("file") MultipartFile excel) {
		ExcelParser parser = new ExcelParser();
		String result = parser.importEtudiants(excel);
		if(result == "error")
			return result;
		List<Etudiant> etudiants = parser.getEtudiants();

		for (Etudiant e : etudiants) {
			etudiantService.saveEtudiant(e);
		}
		
		
		return result;
	}
	
	@PostMapping("excel/note")
	public String importNotes(@RequestParam("file") MultipartFile excel) {
		ExcelParser parser = new ExcelParser();
		parser.importNotes(excel);
		List<Note> notes = parser.getNotes();
		for (Note note : notes) {
			note.setEtudiant(etudiantService.getEtudiantByNumero(note.getEtudiant().getNumero()));
			note.setMatiere(matiereService.findMatiereByCode(note.getMatiere().getCode()));
			note.setUnite(note.getMatiere().getUnite());
			noteService.saveNote(note);
		}
		return "";
	}
}