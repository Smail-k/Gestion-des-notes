package com.polytech.notes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polytech.notes.models.Etudiant;
import com.polytech.notes.repositories.EtudiantRepository;

@Service
public class EtudiantServiceDefault implements EtudiantService{

	@Autowired
	private EtudiantRepository repository;
	
	@Override
	public Etudiant saveEtudiant(Etudiant e) {
		return repository.save(e);
	}

}
