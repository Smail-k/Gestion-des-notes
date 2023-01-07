package com.polytech.notes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.polytech.notes.models.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long>{

}