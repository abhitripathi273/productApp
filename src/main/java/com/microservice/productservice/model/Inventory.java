package com.microservice.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.redis.core.RedisHash;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("inventory")
public class Inventory {

    @Id
    private String productCategory;
    private int stock;
}
