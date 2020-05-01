package com.springboot.service;

import com.springboot.model.Product;

public interface ProductRestService {

	public void setProduct(String id, Product product);
	public Product getProductById(String id);


}
