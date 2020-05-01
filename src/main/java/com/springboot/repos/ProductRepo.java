package com.springboot.repos;

import com.springboot.model.Product;

public interface ProductRepo {
	
	public void setProduct(String id, Product product);
	public Product getProductById(String id);

}
