package com.polytech.notes.services;

import org.springframework.stereotype.Service;

import com.polytech.notes.models.Unite;

@Service
public interface UniteService {

	Unite saveModule(Unite m);
	Unite findUniteByLibelle(String libelle);
}
