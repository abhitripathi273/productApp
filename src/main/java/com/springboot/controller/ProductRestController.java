package com.springboot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.exception.ProductNotFoundException;
import com.springboot.model.Product;
import com.springboot.repos.ProductRepo;

@RestController
@RequestMapping("/shop")
public class ProductRestController {

	@Autowired
	private ProductRepo repo;

	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public Product getProductById(@PathVariable("id") String id) throws ProductNotFoundException {
		System.out.println("Started!!!");
		Optional<Product> product = repo.findById(Long.parseLong(id));
		System.out.println("Product fetched!!!");
		if (!product.isPresent())
			throw new ProductNotFoundException("Product ID " + id + " is not present in DB");
		return product.get();

	}
	
	/*
	 * @GetMapping(ITEM_ENDPOINT_V1 + "/{id}") public Mono<ResponseEntity<Item>>
	 * getItemById(@PathVariable String id) { return
	 * itemReactiveRepository.findById(id) .map(item -> new ResponseEntity<>(item,
	 * HttpStatus.OK)) .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	 * }
	 */
}
