package com.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.model.Product;
import com.springboot.repos.ProductRepo;

@Service
public class ProductRestServiceImpl implements ProductRestService{

	@Autowired
	private ProductRepo repository;
	@Override
	public void setProduct(String id, Product product) {
		repository.setProduct(id, product);
		
	}

	@Override
	public Product getProductById(String id) {
		return repository.getProductById(id);
	}
	

}
