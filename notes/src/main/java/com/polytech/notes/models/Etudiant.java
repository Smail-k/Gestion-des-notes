package com.polytech.notes.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity

public class Etudiant {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;
	@Column(unique = true)
	private String numero;
	@OneToMany(targetEntity = Note.class,cascade = CascadeType.ALL)
	//@JsonIgnore
	@JoinColumn(name="etudiant_id",referencedColumnName = "id")
	private List<Note> notes;
	private Promotion promotion;
	private Long annee;
	
	public Etudiant() {
		super();
	}

	public Etudiant(String nom, String prenom, List<Note> notes, Promotion promotion) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.notes = notes;
		this.promotion = promotion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public Long getAnnee() {
		return annee;
	}

	public void setAnnee(Long annee) {
		this.annee = annee;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	} 
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getNumero() {
		return numero;
	}
	
	
}
