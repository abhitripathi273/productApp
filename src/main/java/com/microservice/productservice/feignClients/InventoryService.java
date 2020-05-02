package com.microservice.productservice.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.productservice.model.Inventory;

@FeignClient(name = "inventory-management-service", url = "localhost:8900")
public interface InventoryService {

	@GetMapping(path = "/inventory/restock/{productType}")
	public ResponseEntity<Inventory> restockProduct(@PathVariable("productType") String productType);

	@GetMapping(path = "/inventory/purchase/{productType}")
	public ResponseEntity<Inventory> purchaseProduct(@PathVariable("productType") String productType);

}