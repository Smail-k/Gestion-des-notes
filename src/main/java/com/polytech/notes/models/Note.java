package com.polytech.notes.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity

public class Note {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double note; 
	private boolean situation; 	
	@ManyToOne()
	private Matiere matiere; 
	//private Etudiant etudiant;
	
	public Note(double note, boolean situation, Matiere matiere) {
		super();
		this.note = note;
		this.situation = situation;
		this.matiere = matiere;
	}
	
	public Note() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getNote() {
		return note;
	}
	public void setNote(double note) {
		this.note = note;
	}
	public boolean isSituation() {
		return situation;
	}
	public void setSituation(boolean situation) {
		this.situation = situation;
	}
	public Matiere getMatiere() {
		return matiere;
	}
	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
	
	
	
	
}