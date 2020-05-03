package com.microservice.productservice.service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
import com.microservice.productservice.model.Gridwall;
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
	public Product addProduct(Product product) {
		LOGGER.debug("Inside service method:: addProduct");
		product.setId(Long.valueOf(count.incrementAndGet()));
		inventory.restockProduct(product.getProductCategory());
		LOGGER.debug("Inventory updated and product added.");
		return repository.save(product);
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
	public Gridwall getAllProducts(Integer pageNo, Integer pageSize, String sortBy) {
		LOGGER.debug("Inside service method:: getAllProducts");
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<Product> pagedResult = repository.findAll(paging);
		Gridwall gridwall = new Gridwall();
		gridwall.setTotalProducts((int) (repository.count()));
		gridwall.setTotalPages(pagedResult.getTotalPages());
		LOGGER.info("Fetching product list");
		if (pagedResult.hasContent()) {
			LOGGER.debug("Products found");
			gridwall.setProductList(pagedResult.stream().map(i -> i).collect(Collectors.toList()));
			return gridwall;
		} else {
			LOGGER.debug("No products in the list returning empty list.");
			return new Gridwall();
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

}
