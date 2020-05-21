package com.nitesh.pmr.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nitesh.pmr.Repository.CategoryRepo;
import com.nitesh.pmr.domain.Category;

@Controller
@RequestMapping("")
public class HomeController {
	
	@Autowired
	CategoryRepo catRepo;
	
	@GetMapping("")
	public String showHome() {
		return "home";
	}
	
	@GetMapping("/category")
	public String showCategories(Model model) {
		Iterable<Category> categories = catRepo.findAll();
		model.addAttribute("category", categories);
		return "category-view";
		
	}
}
