package com.nitesh.pmr.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nitesh.pmr.Repository.CategoryRepo;
import com.nitesh.pmr.Repository.ProductsRepo;
import com.nitesh.pmr.domain.Category;
import com.nitesh.pmr.domain.Products;


@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	CategoryRepo catRepo;

	@Autowired
	ProductsRepo proRepo;
	
	public static String uploadDirectory = System.getProperty("user.dir")+"/Product-images";
	public static String uploadLocation = "/images/";
	
	

	@GetMapping("")
	public String showAdminDashBoard() {
		return "admin";
	}

	@GetMapping("/new-category")
	public String newCategory(Model model, Category category) {

		Iterable<Products> products = proRepo.findAll();

		model.addAttribute("products", products);
		model.addAttribute("category", category);
		return "add-category";
	}

	@PostMapping("/new-category/save")
	public String saveCategory(Category category) {
		catRepo.save(category);

		
		return "redirect:/admin";
	}
	

	@GetMapping("/new-product")
	public String newProduct(Model model, Products product){

		Iterable<Category> categories = catRepo.findAll();

		model.addAttribute("product", product);
		model.addAttribute("allCategory", categories);

		return "add-product";
	}

	@PostMapping("/new-product/save")
	public String saveProduct(Products product, Model model,
			@RequestParam("files") MultipartFile[] files) {

		proRepo.save(product);
		
		
		StringBuilder fileNames = new StringBuilder();
		  for (MultipartFile file : files) {
			  Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
			  
			  product.setImages("images/" + file.getOriginalFilename());
			  
			  fileNames.append(file.getOriginalFilename()+" ");
			  try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		  }
		  
		  proRepo.save(product);
		  
		  model.addAttribute("msg", "Successfully uploaded files "+fileNames.toString());
		  model.addAttribute("imageName", uploadLocation+ "" +product.getImages());
		  
		  
		  
		return "redirect:/admin";
	}
}
