package com.microservice.productservice.service;


import com.microservice.productservice.exception.ProductNotFoundException;
import com.microservice.productservice.model.Gridwall;
import com.microservice.productservice.model.Product;

public interface ProductRestService {

	 Product fetchProductById(String id) throws ProductNotFoundException;
	 Product addProduct(Product product);
	 Gridwall getAllProducts(Integer pageNo, Integer pageSize, String sortBy);
	 Product placeOrderByProductId(String id);

}
