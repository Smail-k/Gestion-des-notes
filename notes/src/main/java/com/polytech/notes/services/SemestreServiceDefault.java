package com.polytech.notes.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polytech.notes.models.Semestre;
import com.polytech.notes.repositories.SemestreRepository;

@Service
public class SemestreServiceDefault implements SemestreService{

	@Autowired
	private SemestreRepository repository;
	
	@Override
	public Semestre saveSemestre(Semestre s) {
		return repository.save(s);
	}

	@Override
	public Semestre findSemestreById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Semestre findSemestreByCode(String code) {
		return repository.findSemestreByCode(code);
	}

	
}