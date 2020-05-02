package com.microservice.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.productservice.exception.ProductNotFoundException;
import com.microservice.productservice.model.Product;
import com.microservice.productservice.service.ProductRestService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ProductRestController {

	@Autowired
	ProductRestService productService;
	

	@GetMapping("/shop/products/{id}")
	@HystrixCommand(groupKey="ProductMicroService", fallbackMethod="getProductByIdFallback", commandKey="getProductById")
	public Product getProductById(@PathVariable String id) throws ProductNotFoundException {
		System.out.println("getProductById: Started!!!");
		//return productService.getProductById(id);
		return productService.fetchProductById(id);
	}

	@PostMapping("/shop/products")
	@HystrixCommand(groupKey="ProductMicroService", fallbackMethod="addProductFallback")
	public String addProduct(@RequestBody Product product) throws JsonProcessingException {
		System.out.println("addProduct: Started!!!");
		//productService.setProduct(String.valueOf(product.getId()), product);
		productService.addProduct(product);
		return "SUCCESS";

	}
	
	/**
	 * Gets the product by id fallback.
	 *
	 * @param id
	 *            the id
	 * @return the product by id fallback
	 */
	public Product getProductByIdFallback(@PathVariable("id") String id, Throwable throwable) {
		if(throwable instanceof ProductNotFoundException){
			System.out.println("Product: " + id + " not found");
		}else if(throwable instanceof Exception){
			System.out.println("System is down!");
		}
		return new Product();
	}

	/**
	 * Adds the product fallback.
	 *
	 * @param product
	 *            the product
	 * @return the string
	 */
	public String addProductFallback(@RequestBody Product product) {
		System.out.println("Adding product: " + String.valueOf(product.getId()) + " failed");
		return "FAILED";

	}
}
