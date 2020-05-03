package com.microservice.productservice.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("gridwall")
public class Gridwall {
	
	private List<Product> productList = new ArrayList<>();
	private Integer totalProducts;
	private Integer totalPages;

}
