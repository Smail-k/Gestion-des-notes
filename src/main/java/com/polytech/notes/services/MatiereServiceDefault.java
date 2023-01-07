package com.polytech.notes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polytech.notes.models.Matiere;
import com.polytech.notes.models.Unite;
import com.polytech.notes.repositories.MatiereRepository;

@Service
public class MatiereServiceDefault implements MatiereService{

	@Autowired
	private MatiereRepository repository;

	@Override
	public Matiere saveMatiere(Matiere m) {
		return repository.save(m);
	}

	@Override
	public Matiere findMatiereById(Long id) {
		return repository.findMatiereById(id);
	}

	@Override
	public List<Matiere> findMatiereByUnite(Unite u) {
		return repository.findMatiereByUnite(u);
	}
	
	


}
