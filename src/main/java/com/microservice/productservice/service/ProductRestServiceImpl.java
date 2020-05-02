package com.microservice.productservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.productservice.exception.ProductNotFoundException;
import com.microservice.productservice.model.Product;
import com.microservice.productservice.repos.ProductManagementRepository;
import com.microservice.productservice.repos.ProductRepo;

@Service
public class ProductRestServiceImpl implements ProductRestService {

	@Autowired
	// private ProductRepo repository;
	// Using JPA repository for operation
	private ProductManagementRepository repository;

	@Override
	public void addProduct(Product product) {
		repository.save(product);
	}

	@Override
	public Product fetchProductById(String id) throws ProductNotFoundException {
		System.out.println("Inside service method:: fetchProductById" + id);
		Optional<Product> record = repository.findById(Long.parseLong(id));
		System.out.println("Fetching product details by id");
		if (record.isPresent()) {
			System.out.println("Product found!!");
			return record.get();
		} else {
			throw new ProductNotFoundException("No record found with given product Id");
		}

	}

	/*
	 * @Override public void setProduct(String id, Product product) {
	 * repository.setProduct(id, product);
	 * 
	 * }
	 * 
	 * @Override public Product getProductById(String id) { return
	 * repository.getProductById(id); }
	 */

}
