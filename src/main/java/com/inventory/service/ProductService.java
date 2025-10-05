package com.inventory.service;

import java.util.List;

import com.inventory.model.Product;

public interface ProductService {

	void addProduct(Product product);

	void deleteProduct(int id);

	Product getProductById(int id);

	List<Product> getListByName(String name);

	Product increaseQuantityByOne(int id);

	Product decreaseQuantityByOne(int id);

	List<Product> getAllProducts();
	void updateProduct(Product product);

	Product increaseStock(int id, int qty);

	Product decreaseStock(int id, int qty);

	List<Product> findLowStockProduct();

	

	

}
