package com.microservice.productservice.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.microservice.productservice.model.Product;

@Repository
public class ProductRepoImpl implements ProductRepo{

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Override
	public void setProduct(String id, Product product) {
		redisTemplate.opsForValue().set(id, product);
	}

	@Override
	public Product getProductById(String id) {
		return (Product) redisTemplate.opsForValue().get(id);
	}
	
	

}
