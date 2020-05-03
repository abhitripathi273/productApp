package com.microservice.productservice.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Gridwall {
	
	private List<Product> productList = new ArrayList<>();
	private Integer totalProducts;
	private Integer totalPages;

}
