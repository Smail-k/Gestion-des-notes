package com.polytech.notes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polytech.notes.models.Note;
import com.polytech.notes.models.Unite;
import com.polytech.notes.repositories.UniteRepository;

@Service
public class UniteServiceDefault implements UniteService{

	@Autowired
	private UniteRepository repository;

	@Override
	public Unite saveModule(Unite u) {
		if(repository.findUniteByCode(u.getCode())==null)
			return repository.save(u);
		return null;
	}

	@Override
	public Unite findUniteByLibelle(String libelle) {
		return repository.findUniteBylibelle(libelle);
	}

	@Override
	public Unite findUniteByCode(String codeUnite) {
		return repository.findUniteByCode(codeUnite);
	}
	 


}
