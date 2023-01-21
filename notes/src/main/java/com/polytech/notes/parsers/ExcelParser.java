package com.polytech.notes.parsers;

import java.io.IOException;
import java.time.Year;
import java.util.List;
import java.util.Vector;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.polytech.notes.models.Etudiant;
import com.polytech.notes.models.Matiere;
import com.polytech.notes.models.Note;
import com.polytech.notes.models.Promotion;
import com.polytech.notes.models.Semestre;
import com.polytech.notes.models.Unite;
import com.polytech.notes.services.EtudiantService;
import com.polytech.notes.services.EtudiantServiceDefault;

public class ExcelParser {

	private List<Semestre> semestres=new Vector<Semestre>();
	private List<Unite> unites=new Vector<Unite>();
	private List<Matiere> matieres=new Vector<Matiere>();
	private List<Etudiant> etudiants = new Vector<Etudiant>();
	private List<Note> notes = new Vector<Note>();
	
	@Autowired
	private EtudiantService service=new EtudiantServiceDefault(); 
	
	public String excelReader(MultipartFile excel) {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(excel.getInputStream());
			XSSFSheet sheet = workbook.getSheetAt(0);
			Semestre s=null;
			Unite u=null;
			for(int i=4; i<sheet.getPhysicalNumberOfRows();i++) {
				XSSFRow row = sheet.getRow(i);
				if((row.getCell(0)+"").startsWith("SEM")) {
					s=new Semestre();
					s.setNom(row.getCell(0)+"");
					s.setCode(row.getCell(1)+"");
					semestres.add(s);
					//continue;
				}
				
				else if((row.getCell(0)+"").equals("UE")) {
					u=new Unite();
					u.setCode(row.getCell(1)+"");
					u.setLibelle((row.getCell(2)+"").trim());
					u.setSemestre(s);
					if(row.getCell(5)!= null &&  (row.getCell(5)+"").trim()!="")
						u.setCoefficient(Double.parseDouble((row.getCell(5)+"").trim()));
					s.getUnites().add(u);
					unites.add(u);
					if(!row.getCell(3).toString().equals("")) {
						Matiere m = new Matiere();
						m.setLibelle(row.getCell(3)+"");
						if(row.getCell(5)!= null &&  (row.getCell(5)+"").trim()!="")
							m.setCoefficient(Double.parseDouble((row.getCell(5)+"").trim()));
						m.setCode(row.getCell(1)+"");
						m.setUnite(u);
						if(row.getCell(1)!=null) {
							u.getMatieres().add(m);
							matieres.add(m);
						}
					}
				}else if((row.getCell(0)+"").equals("")){
					Matiere m = new Matiere();
					m.setLibelle(row.getCell(3)+"");
					m.setCode(row.getCell(1)+"");
					m.setUnite(u);
					if(row.getCell(3)+"" == "") {
						m.setLibelle(row.getCell(2)+"");
					}
						
					if(row.getCell(5)!= null &&  (row.getCell(5)+"").trim()!="")
						m.setCoefficient(Double.parseDouble((row.getCell(5)+"").trim()));
					if(row.getCell(1)!=null) {
						u.getMatieres().add(m);
						matieres.add(m);
					}
				}
				
					
			}
			
		} catch (IOException e) {
			return "Erreur de format de fichier";
		}
		return "success";
	}
	
	public String importEtudiants(MultipartFile excel) {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(excel.getInputStream());
			XSSFSheet sheet = workbook.getSheetAt(0);
			XSSFRow row = sheet.getRow(0);
			
			Promotion p = (row.getCell(0)+"").trim().contains("3A") ? Promotion.Annee3
					: (row.getCell(0)+"").trim().contains("4A") ? Promotion.Annee4
					: Promotion.Annee5;
			String annee = (row.getCell(0)+"").trim().substring((row.getCell(0)+"").trim().length()-4);
			Long l=Long.parseLong(annee);
			l++;
			annee+="/"+l; //2022/2023 par exemple
			
			for(int i=3; i<=sheet.getPhysicalNumberOfRows();i++) {
				row = sheet.getRow(i);
				Etudiant e = new Etudiant();
				e.setNumero((row.getCell(0)+"").trim().substring(0, (row.getCell(0)+"").trim().length()-2));
				e.setNom((row.getCell(1)+"").trim());
				e.setPrenom((row.getCell(2)+"").trim());
				e.setPromotion(p);
				e.setAnnee(annee);
				
				etudiants.add(e);
			}
			workbook.close();
			return "success";
		} catch (IOException e) {
			System.out.println("error");
			return "error";
		}
	}
	
	public String importNotes(MultipartFile excel) {
		XSSFWorkbook workbook;
		try {
			workbook = new XSSFWorkbook(excel.getInputStream());
			XSSFSheet sheet = workbook.getSheetAt(0);
			XSSFRow row = sheet.getRow(0);
//			int nbrMatieres = row.getPhysicalNumberOfCells(); 
//			Matiere m;
//			List<Matiere> matiere = new Vector<Matiere>();
//			for (int i = 3; i < nbrMatieres; i++) {
//				if((row.getCell(i)+"").contains("-")) {
//					m=new Matiere();
//					m.setCode((row.getCell(i)+"").split("-")[0].trim());
//					matiere.add(m);
//				}
//			}
//			//List<Note> notes = new Vector<Note>();
//			Note note = new Note();
//			for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
//				row=sheet.getRow(i);
//				Etudiant e = service.getEtudiantByNumero((row.getCell(0)+"").trim());
//				if(e==null)
//					System.out.println("etudiant se trouve pas");
//				else 
//					note.setEtudiant(e);
//			}
//			
//			
//			
			int nbrMatieres = row.getPhysicalNumberOfCells(); 
			for (int i = 3; i < nbrMatieres; i++) {
				row = sheet.getRow(0);
				Matiere m = new Matiere();
				m.setCode((row.getCell(i)+"").trim().split("-")[0]); 
				for (int j = 2; j < sheet.getPhysicalNumberOfRows(); j++) {
					row=sheet.getRow(j);
					Note note = new Note();
					Double noteValue;
					try {
						noteValue=Double.parseDouble((row.getCell(i)+"").trim());
					} catch (Exception e) {
						noteValue=0.0;
					}
					note.setNote(noteValue);
					note.setMatiere(m);
					note.setSituation(note.getNote()>=6.0 ? true : false);
					note.setAnnee(Year.now().getValue()+"");
					Etudiant e = new Etudiant();
					e.setNumero((row.getCell(0)+"").trim());
					e.setNom((row.getCell(1)+"").trim());
					e.setPrenom((row.getCell(2)+"").trim());
					note.setEtudiant(e);
					notes.add(note);
				}
			}
			workbook.close();
			return "success";
			
		} catch (IOException e) {
			e.printStackTrace();
			return "Error";
		}
		
	}
	
	
	public List<Semestre> getSemestres() {
		return semestres;
	}
	public List<Unite> getUnites() {
		return unites;
	}
	public List<Matiere> getMatieres() {
		return matieres;
	}
	public List<Etudiant> getEtudiants() {
		return etudiants;
	}
	
	public List<Note> getNotes() { 
		return notes;
	}
}
