package com.microservice.productservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.productservice.exception.ProductNotFoundException;
import com.microservice.productservice.feignClients.InventoryService;
import com.microservice.productservice.model.Product;
import com.microservice.productservice.repos.ProductManagementRepository;
import com.microservice.productservice.repos.ProductRepo;

@Service
public class ProductRestServiceImpl implements ProductRestService {

	@Autowired
	// private ProductRepo repository;
	// Using JPA repository for operation
	private ProductManagementRepository repository;

	@Autowired
	private InventoryService inventory;

	@Override
	public void addProduct(Product product) {
		inventory.restockProduct(product.getProductCategory());
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

	@Override
	public List<Product> getAllProducts(Product product) {
		return repository.findAll();
	}

	@Override
	public Product placeOrderByProductId(String id) {
		Product orderedProduct = new Product();
		repository.findById(Long.parseLong(id)).ifPresent(product -> {
			orderedProduct.setId(product.getId());
			orderedProduct.setName(product.getName());
			orderedProduct.setLongDescription(product.getLongDescription());
			orderedProduct.setShortDescription(product.getShortDescription());
			orderedProduct.setProductCategory(product.getProductCategory());
			orderedProduct.setPrice(product.getPrice());
			inventory.purchaseProduct(product.getProductCategory());
			repository.delete(product);
		});
		System.out.println("Order placed!!!");
		return orderedProduct;

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
