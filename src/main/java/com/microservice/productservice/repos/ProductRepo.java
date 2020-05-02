package com.microservice.productservice.repos;

import com.microservice.productservice.model.Product;

public interface ProductRepo {
	
	public void setProduct(String id, Product product);
	public Product getProductById(String id);

}
