package com.microservice.productservice.repos;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.microservice.productservice.model.Product;

public interface ProductManagementRepository extends PagingAndSortingRepository<Product, Long> {


}
