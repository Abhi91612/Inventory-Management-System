package com.inventory.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.inventory.exceptions.ProductNotFoundException;
import com.inventory.model.Product;
import com.inventory.service.ProductService;

@RestController
@RequestMapping("product")
public class ProductController {

	private ProductService productService;
	
	public ProductController(ProductService productService) {
		 this.productService=productService;
	}

	@GetMapping
	public ResponseEntity<List<Product>> getAllList(){
		return ResponseEntity.ok( productService.getAllProducts()) ;
		}
	
	@PostMapping(value = "add" , produces = {"application/json"}, consumes = {"application/json"})
	public ResponseEntity<Product> addProduct(@RequestBody Product product){
		productService.addProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
		
	}
	
	@GetMapping("getbyid/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id){
		Product product=productService.getProductById(id);
		return ResponseEntity.ok(product);
	}
	
	@GetMapping(value = "list/byname")
	public ResponseEntity<List<Product>> ProductListbyName(@RequestParam String name){
		List<Product> pList=productService.getListByName(name);
		if(pList==null) {
			 throw new ProductNotFoundException("products not found by the given name .......");
		}
		return ResponseEntity.ok(pList);
	}
	
	// Increase one stock at time
	@PutMapping("increase/quantity/byOne/{id}")
	public ResponseEntity<Product> increaseStockQuantityByOne(@PathVariable int id ){
		Product product=productService.increaseQuantityByOne(id);
		return ResponseEntity.ok(product);
		
		
		
	}
	
	//decrease one stock at a time
	@PutMapping("decrease/quantity/byOne/{id}")
	public ResponseEntity<Product> decreaseStockQuantityByOne(@PathVariable int id ){
		Product product=productService.decreaseQuantityByOne(id);
		return ResponseEntity.ok(product);	
	}
	
    // Increase multiple stock at time
    @PutMapping("/{id}/increase/{qty}")
		public ResponseEntity<Product> increaseStock(@PathVariable int id, @PathVariable int qty) {
		    Product updatedProduct = productService.increaseStock(id, qty);
		    return ResponseEntity.ok(updatedProduct);
	}

    // Decrease multiple stock at time
    @PutMapping("/{id}/decrease/{qty}")
    public ResponseEntity<Product> decreaseStock(@PathVariable int id, @PathVariable int qty) {
        Product updatedProduct = productService.decreaseStock(id, qty);
        return ResponseEntity.ok(updatedProduct);
    }
	   
    @GetMapping("list/low-stock")
    public ResponseEntity<List<Product>> getLowStockProducts(){
    	return ResponseEntity.ok(productService.findLowStockProduct());
    }
    
    
	@PutMapping(value = "updateByid/{id}")
	public ResponseEntity<Product> updatebyid(@PathVariable int id,@RequestBody Product product){
		Product producto=productService.getProductById(id);
			if(producto==null) {
				productService.addProduct(product);
				return ResponseEntity.status(HttpStatus.CREATED).body(product);
		    }
		product.setId(id);
		productService.updateProduct(product);
		return ResponseEntity.ok(product);
		}
	

	
	@DeleteMapping("deletebyid/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable int id){
		Product product=productService.getProductById(id);
		if(product==null) {
			throw new ProductNotFoundException("Id = "+id+" is not exist in the database ....");
		}
		productService.deleteProduct(id);
		return ResponseEntity.ok(product);
	}
		
	
	
}
