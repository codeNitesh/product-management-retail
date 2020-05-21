package com.nitesh.pmr.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nitesh.pmr.domain.Products;

public interface ProductsRepo extends CrudRepository<Products, Long>{
	
	@Query(value = "SELECT * FROM PRODUCTS WHERE CATEGORY_ID = ?1",
    countQuery = "SELECT count(*) FROM PRODUCTS WHERE CATEGORY_ID = ?1",
    nativeQuery = true)
	List<Products> findByCategory(long categoryId);
	
	
	
	
	List<Products> findByNameOrDescriptionContaining(String name, String description);
}
