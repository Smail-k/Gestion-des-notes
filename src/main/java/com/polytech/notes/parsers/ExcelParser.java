package com.polytech.notes.parsers;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.polytech.notes.models.Etudiant;
import com.polytech.notes.models.Matiere;
import com.polytech.notes.models.Semestre;
import com.polytech.notes.models.Unite;

public class ExcelParser {

	private List<Semestre> semestres=new Vector<Semestre>();
	private List<Unite> unites=new Vector<Unite>();
	private List<Matiere> matieres=new Vector<Matiere>();
	private List<Etudiant> etudiants = new Vector<Etudiant>();
	
	
	public void excelReader(MultipartFile excel) {
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
			System.out.println("error");
		}
	}
	
	public String importEtudiants(MultipartFile excel) {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(excel.getInputStream());
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			for(int i=3; i<=sheet.getPhysicalNumberOfRows();i++) {
				XSSFRow row = sheet.getRow(i);
				Etudiant e = new Etudiant();
				e.setNumero((row.getCell(0)+"").trim().substring(0, (row.getCell(0)+"").trim().length()-2));
				e.setNom((row.getCell(1)+"").trim());
				e.setPrenom((row.getCell(2)+"").trim());
				
				etudiants.add(e);
			}
			workbook.close();
			return "success";
		} catch (IOException e) {
			System.out.println("error");
			return "error";
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
}
