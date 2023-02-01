package com.polytech.notes.controllers;


import java.util.List;

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
		String parsingMessage = parser.excelReader(excel);
		if(!parsingMessage.equals("success"))
			return "Erreur de format de fichier (contenu)";
		
		String feedback="importation avec succée";
		
		List<Semestre> semestres = parser.getSemestres();
		if(semestres.size()==0)
			return "erreur d'importation vers la base de donnees !";
		for (Semestre semestre : semestres) {
			Semestre s= semestreService.saveSemestre(semestre);
			if(s==null)
				feedback="erreur d'importation vers la base de donnees !";
		}
	
		return feedback;
		
	}
	
	@PostMapping("/excel/etudiant")
	public String importEtudiants(@RequestParam("file") MultipartFile excel) {
		ExcelParser parser = new ExcelParser();
		String result = parser.importEtudiants(excel);
		if(result == "error")
			return result;
		List<Etudiant> etudiants = parser.getEtudiants();

		for (Etudiant e : etudiants) {
			Etudiant et = etudiantService.saveEtudiant(e);
			if(et==null)
				result="erreur d'ajout de certain etudiants !!";
		}
		
		
		return result;
	}
	
	@PostMapping("excel/note")
	public String importNotes(@RequestParam("file") MultipartFile excel) {
		ExcelParser parser = new ExcelParser();
		parser.importNotes(excel);
		List<Note> notes = parser.getNotes();
		Note test=null;
		for (Note note : notes) {
			note.setEtudiant(etudiantService.getEtudiantByNumero(note.getEtudiant().getNumero()));
			note.setMatiere(matiereService.findMatiereByCode(note.getMatiere().getCode()));
			//note.setUnite(note.getMatiere().getUnite());
			test = noteService.saveNote(note);
		}
		for (Note note : notes) {
			String uniteCode= note.getMatiere().getUnite().getCode();
			String etudiantNumero= note.getEtudiant().getNumero();
			
			Note n = noteService.getNoteByUniteCodeAndEtudiantNumero(uniteCode, etudiantNumero);
			
			List<Note> list = noteService.getNoteByMatiereUnite(uniteCode, etudiantNumero);
			double noteModule=0;
			for (Note noteMatiere : list) {
				noteModule+=noteMatiere.getNote()*noteMatiere.getMatiere().getCoefficient();
			}
			noteModule/=note.getMatiere().getUnite().getCoefficient();
			if(n==null) {
				n=new Note();
				n.setNote(noteModule);
				n.setEtudiant(note.getEtudiant());
				n.setAnnee(note.getAnnee());
				n.setUnite(note.getMatiere().getUnite());
				n.setSituation(noteModule>=10 ? true : false);
				noteService.saveNote(n);
			}
			else {
				n.setNote(noteModule);
				noteService.saveNote(n);
			}
		}
		
		return test==null ? "erreur d'ajout de certaines notes !" : "notes bien ajoutées";
	}
}