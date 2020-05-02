package com.microservice.productservice.service;

import java.util.List;

import com.microservice.productservice.exception.ProductNotFoundException;
import com.microservice.productservice.model.Product;

public interface ProductRestService {

	 //void setProduct(String id, Product product);
	 //Product getProductById(String id);
	 Product fetchProductById(String id) throws ProductNotFoundException;
	 void addProduct(Product product);
	 List<Product> getAllProducts(Integer pageNo, Integer pageSize, String sortBy);
	 Product placeOrderByProductId(String id);

}
