package com.polytech.notes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polytech.notes.models.Semestre;

@Repository
public interface SemestreRepository extends JpaRepository<Semestre, Long>{

	Semestre findSemestreByCode(String code);
}
