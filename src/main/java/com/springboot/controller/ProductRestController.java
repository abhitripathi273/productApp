package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springboot.exception.ProductNotFoundException;
import com.springboot.model.Product;
import com.springboot.service.ProductRestService;

@RestController
@RequestMapping("/shop")
public class ProductRestController {

	/*
	 * @Autowired private ProductRepo repo;
	 */

	@Autowired
	ProductRestService productService;

	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	@HystrixCommand(groupKey="ProductMicroService", fallbackMethod="getProductByIdFallback", commandKey="getProductById")
	public Product getProductById(@PathVariable("id") String id) throws ProductNotFoundException {
		System.out.println("getProductById: Started!!!");
		return productService.getProductById(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/products")
	@HystrixCommand(groupKey="ProductMicroService", fallbackMethod="addProductFallback")
	public String addProduct(@RequestBody Product product) throws JsonProcessingException {
		System.out.println("addProduct: Started!!!");
		ObjectMapper mapper = new ObjectMapper();
		productService.setProduct(String.valueOf(product.getId()), product);
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
