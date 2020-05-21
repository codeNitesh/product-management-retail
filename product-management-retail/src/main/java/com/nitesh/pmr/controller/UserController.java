package com.nitesh.pmr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nitesh.pmr.Repository.CategoryRepo;
import com.nitesh.pmr.Repository.ProductsRepo;
import com.nitesh.pmr.domain.Category;
import com.nitesh.pmr.domain.Products;


@Controller
@RequestMapping("/products")
public class UserController {
	
	@Autowired
	ProductsRepo proRepo;
	
	@Autowired
	CategoryRepo catRepo;
	
	
	public static String uploadLocation = "/images/";
	
	
	@GetMapping("")
	public String showProducts(Model model) {
		
		Iterable<Products> products = proRepo.findAll();
 		model.addAttribute("product", products);
 		
 		Iterable<Category> categories = catRepo.findAll();
 		model.addAttribute("category", categories);
 		
 		
 		//model.addAttribute("imageName", uploadLocation+ "" + ((Products) products).getImages());
 		
		return "products";
	}
	
	
	
	@GetMapping("/{categoryId}")
	public String choosenCategory(@PathVariable long categoryId, Model model) {
		
		List<Products> products = proRepo.findByCategory(categoryId);
		model.addAttribute("product", products);
		
		Iterable<Category> categories = catRepo.findAll();
 		model.addAttribute("category", categories);
		
		return "category";
	}
	
	
	@PostMapping("/search")
	public String search(@RequestParam String query, Model model) {
		
		List<Products> products = proRepo.findByNameOrDescriptionContaining(query, query);
		model.addAttribute("product", products);
		return "products";
	}
	
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
}
