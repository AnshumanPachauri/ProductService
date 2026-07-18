package com.ap.microservices.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ap.microservices.product.model.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

}
