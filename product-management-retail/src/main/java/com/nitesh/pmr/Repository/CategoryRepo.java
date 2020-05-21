package com.nitesh.pmr.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nitesh.pmr.domain.Category;
import com.nitesh.pmr.domain.Products;

public interface CategoryRepo extends CrudRepository <Category, Long>{

	List<Products> findBycategoryId(long categoryId);
	
}
