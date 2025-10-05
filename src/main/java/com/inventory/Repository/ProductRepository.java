package com.inventory.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.inventory.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findAllByName(String name);
	
	 @Query("SELECT p FROM Product p  WHERE p.stockQuantity < p.lowStockThreshold")
	  List<Product> findProductsBelowThreshold();
	
	

}
