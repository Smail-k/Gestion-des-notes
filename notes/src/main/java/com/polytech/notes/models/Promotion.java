package com.polytech.notes.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Promotion {

	@Id
	private String promo;

	public Promotion() {
		// TODO Auto-generated constructor stub
	}

	public Promotion(String promo) {
		super();
		this.promo = promo;
	}

	public String getPromo() {
		return promo;
	}

	public void setPromo(String promo) {
		this.promo = promo;
	}
	
	
}
