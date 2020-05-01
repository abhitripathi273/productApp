package com.springboot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.exception.ProductNotFoundException;
import com.springboot.model.Product;
import com.springboot.repos.ProductRepo;
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
	public Product getProductById(@PathVariable("id") String id) throws ProductNotFoundException {
		System.out.println("Started!!!");
		return productService.getProductById(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/products")
	public void addProduct(@RequestBody Product product) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		productService.setProduct(String.valueOf(product.getId()), product);

	}
}
