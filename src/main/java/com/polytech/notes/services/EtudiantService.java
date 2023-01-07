package com.polytech.notes.services;

import org.springframework.stereotype.Service;

import com.polytech.notes.models.Etudiant;

@Service
public interface EtudiantService {

	Etudiant saveEtudiant(Etudiant e);
	
}