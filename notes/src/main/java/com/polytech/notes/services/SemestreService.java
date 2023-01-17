package com.polytech.notes.services;

import org.springframework.stereotype.Service;

import com.polytech.notes.models.Semestre;

@Service
public interface SemestreService {

	Semestre saveSemestre(Semestre s);
	Semestre findSemestreById(Long id);
	Semestre findSemestreByCode(String code);
}
