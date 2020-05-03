package com.microservice.productservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.microservice.productservice.exception.ProductNotFoundException;
import com.microservice.productservice.feignClients.InventoryService;
import com.microservice.productservice.model.Product;
import com.microservice.productservice.repos.ProductManagementRepository;

@Service
public class ProductRestServiceImpl implements ProductRestService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ProductRestServiceImpl.class);

	@Autowired
	private ProductManagementRepository repository;

	@Autowired
	private InventoryService inventory;
	
	private static final AtomicInteger count = new AtomicInteger(3000);

	@Override
	public void addProduct(Product product) {
		LOGGER.debug("Inside service method:: addProduct");
		product.setId(Long.valueOf(count.incrementAndGet()));
		inventory.restockProduct(product.getProductCategory());
		LOGGER.debug("Inventory updated and product added.");
		repository.save(product);
	}

	@Override
	public Product fetchProductById(String id) throws ProductNotFoundException {
		LOGGER.debug("Inside service method:: fetchProductById" + id);
		Optional<Product> record = repository.findById(Long.parseLong(id));
		LOGGER.debug("Fetching product details by id");
		if (record.isPresent()) {
			LOGGER.debug("Product found!!");
			return record.get();
		} else {
			throw new ProductNotFoundException("No record found with given product Id");
		}

	}

	@Override
	public List<Product> getAllProducts(Integer pageNo, Integer pageSize, String sortBy) {
		LOGGER.debug("Inside service method:: getAllProducts");
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		 
        Page<Product> pagedResult = repository.findAll(paging);
        LOGGER.info("Fetching product list");
        if(pagedResult.hasContent()) {
        	 LOGGER.debug("Products found");
            return pagedResult.getContent();
        } else {
        	 LOGGER.debug("No products in the list returning empty list.");
            return new ArrayList<Product>();
        }
	}

	@Override
	public Product placeOrderByProductId(String id) {
		LOGGER.debug("Inside placeOrderByProductId :: started!!!");
		Product orderedProduct = new Product();
		repository.findById(Long.parseLong(id)).ifPresent(product -> {
			LOGGER.debug("Product found in the list!!!");
			orderedProduct.setId(product.getId());
			orderedProduct.setName(product.getName());
			orderedProduct.setLongDescription(product.getLongDescription());
			orderedProduct.setShortDescription(product.getShortDescription());
			orderedProduct.setProductCategory(product.getProductCategory());
			orderedProduct.setPrice(product.getPrice());
			inventory.purchaseProduct(product.getProductCategory());
			LOGGER.debug("Inventory updated!!!");
			repository.delete(product);
			LOGGER.debug("Product list updated!!!");
		});
		LOGGER.debug("Inside placeOrderByProductId :: end!!!");
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
