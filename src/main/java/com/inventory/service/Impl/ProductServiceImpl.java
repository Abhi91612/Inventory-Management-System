package com.inventory.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.inventory.Repository.ProductRepository;
import com.inventory.exceptions.InvalidStockOperationException;
import com.inventory.exceptions.ProductNotFoundException;
import com.inventory.model.Product;
import com.inventory.service.ProductService;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired ProductRepository productRepository;

    

	@Override
	public void addProduct(Product product) {
		productRepository.save(product);
		
	}
	

	@Override
	public void deleteProduct(int id) {
		productRepository.deleteById(id);
		
	}

	@Override
	public Product getProductById(int id) {
		
		return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Id = " + id + " not exist in the database ...."));
	}

	@Override
	public List<Product> getAllProducts() {
		
		return productRepository.findAll();
	}
	
	@Override
	public List<Product> getListByName(String name) {
		
		return productRepository.findAllByName(name);
	}
	@Override
	@Transactional
	public Product increaseQuantityByOne(int id) {
	    Product product = productRepository.findById(id)
	        .orElseThrow(() -> new ProductNotFoundException(
	            "Id = " + id + " does not exist in the database."));

	    product.setStockQuantity(product.getStockQuantity() + 1);

	    // Save updated product and return
	    return productRepository.save(product);
	}
	
	@Override
	@Transactional
	public Product decreaseQuantityByOne(int id) {
	    Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Id = " + id + " does not exist in the database."));
	    if (product.getStockQuantity() <= 0) {
	        throw new InvalidStockOperationException("Cannot decrease stock. Product ID = " + id + " has out of  stock.");
	    }
	    product.setStockQuantity(product.getStockQuantity() - 1);
	    return productRepository.save(product); 
	}

	@Override
	public void updateProduct(Product product) {
		productRepository.save(product);
		
	}


	@Transactional
    public Product increaseStock(int id, int quantity) {
        Product product = getProductById(id);
        product.setStockQuantity(product.getStockQuantity() + quantity);
        return productRepository.save(product);
    }

    @Transactional
    public Product decreaseStock(int id, int quantity) {
    	Product product = getProductById(id);
        if (product.getStockQuantity() < quantity) {
            throw new InvalidStockOperationException("Cannot decrease. Stock is too low for product id: " + id);
        }
        product.setStockQuantity(product.getStockQuantity() - quantity);
        return productRepository.save(product);
    }


    @Override
	public List<Product> findLowStockProduct() {
		
		return productRepository.findProductsBelowThreshold();
	}

	

	

	

	
	
	
}
