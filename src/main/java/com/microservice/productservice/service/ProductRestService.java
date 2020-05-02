package com.microservice.productservice.service;

import com.microservice.productservice.exception.ProductNotFoundException;
import com.microservice.productservice.model.Product;

public interface ProductRestService {

	 //void setProduct(String id, Product product);
	 //Product getProductById(String id);
	 Product fetchProductById(String id) throws ProductNotFoundException;
	 void addProduct(Product product);

}
