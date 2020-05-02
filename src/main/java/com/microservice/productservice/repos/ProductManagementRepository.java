package com.microservice.productservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.productservice.model.Product;


public interface ProductManagementRepository extends JpaRepository<Product, Long> {

}
