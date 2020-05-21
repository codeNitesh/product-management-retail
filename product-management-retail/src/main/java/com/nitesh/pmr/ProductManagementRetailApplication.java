package com.nitesh.pmr;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nitesh.pmr.controller.AdminController;


@SpringBootApplication

public class ProductManagementRetailApplication {

	public static void main(String[] args) {
		new File(AdminController.uploadDirectory).mkdir();
		SpringApplication.run(ProductManagementRetailApplication.class, args);
	}
}
