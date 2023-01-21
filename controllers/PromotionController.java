package com.polytech.notes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polytech.notes.services.EtudiantService;

@RestController
@RequestMapping("/api/promotion")
public class PromotionController {

	@Autowired
	private EtudiantService service;
	
	@GetMapping("/annees")
	public List<String> getAnneeUniversitaires(){
		return service.getAnneeUniversitaires();
	}
	
	@GetMapping("/promotions")
	public List<String> getPromotions(){
		return service.getPromotions();
	}
	
}
