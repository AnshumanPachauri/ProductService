package com.ap.microservices.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ap.microservices.product.dto.ProductRequest;
import com.ap.microservices.product.model.Product;
import com.ap.microservices.product.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Product createProduct(ProductRequest productRequest) {
		
		Product product = Product.builder()
				.name(productRequest.name())
				.description(productRequest.description())
				.price(productRequest.price())
				.build();
		productRepository.save(product);
		log.info("Product Created Successfully");
		return product;
	}
	
}
