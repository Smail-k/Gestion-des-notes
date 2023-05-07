package com.polytech.notes.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class AnneeUniversitaire {

	@Id
	private int id;
	private String annee;
	
	public AnneeUniversitaire(String annee) {
		this.annee = annee;
	}
	
}