package com.microservice.productservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.productservice.exception.ProductNotFoundException;
import com.microservice.productservice.model.Product;
import com.microservice.productservice.service.ProductRestService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ProductRestController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);
	
	@Autowired
	ProductRestService productService;
	
	@Value("${gridwall.products.page.size}")
	private String pageSizeConfigValue;

	public static final String PRODUCT_LIST_CACHE_KEY = "productListCacheKey";
	
	@GetMapping("/shop/products/{id}")
	@Cacheable(value = "product", key = "#id")
	@HystrixCommand(groupKey = "ProductMicroService", fallbackMethod = "getProductByIdFallback", commandKey = "getProductById")
	public Product getProductById(@PathVariable String id) throws ProductNotFoundException {
		LOGGER.debug("getProductById: Started!!!");
		// return productService.getProductById(id);
		return productService.fetchProductById(id);
	}

	@PostMapping("/shop/products")
	@HystrixCommand(groupKey = "ProductMicroService", fallbackMethod = "addProductFallback")
	public String addProduct(@RequestBody Product product) throws JsonProcessingException {
		LOGGER.debug("addProduct: Started!!!");
		// productService.setProduct(String.valueOf(product.getId()), product);
		productService.addProduct(product);
		return "SUCCESS";

	}

	@GetMapping("/shop/products")
	//@Cacheable(value = "products", key = "#root.target.PRODUCT_LIST_CACHE_KEY")
	@HystrixCommand(groupKey = "ProductMicroService", fallbackMethod = "getAllProductsFallback")
	public List<Product> getAllProducts(@RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "0") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
		LOGGER.debug("Fetch all products ");
		return productService.getAllProducts(pageNo,(pageSize==0?Integer.valueOf(this.pageSizeConfigValue):pageSize),sortBy);

	}
	
	@GetMapping("/shop/placeOrder/product/{id}")
	@HystrixCommand(groupKey = "ProductMicroService", fallbackMethod = "placeOrderByProductIdFallback")
	public Product placeOrderByProductId(@PathVariable String id) throws ProductNotFoundException {
		LOGGER.debug("placeOrderByProductId: Started!!!");
		return productService.placeOrderByProductId(id);
	}

	/**
	 * Gets the product by id fallback.
	 *
	 * @param id the id
	 * @return the product by id fallback
	 */
	public Product getProductByIdFallback(@PathVariable("id") String id, Throwable throwable) {
		if (throwable instanceof ProductNotFoundException) {
			LOGGER.error("Product: " + id + " not found");
		} else if (throwable instanceof Exception) {
			LOGGER.error("System is down!");
		}
		return new Product();
	}

	/**
	 * Adds the product fallback.
	 *
	 * @param product
	 *            the product
	 * @return the string
	 */
	public String addProductFallback(@RequestBody Product product) {
		LOGGER.error("Adding product: " + String.valueOf(product.getId()) + " failed");
		return "FAILED";

	}

	/**
	 * Gets the all products fallback.
	 *
	 * @param product
	 *            the product
	 * @return the all products fallback
	 */
	public List<Product> getAllProductsFallback(@RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "0") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
		LOGGER.error("Could not fetch all products: getAllProductsFallback : START");
		return new ArrayList<>();
	}

	public Product placeOrderByProductIdFallback(@PathVariable String id, Throwable throwable) {
		if (throwable instanceof ProductNotFoundException) {
			LOGGER.error("Could not place order for product:"+id+" placeOrderByProductIdFallback : START");
		} else if (throwable instanceof Exception) {
			LOGGER.error("System is down!");
		}
		return new Product();
	}
}
