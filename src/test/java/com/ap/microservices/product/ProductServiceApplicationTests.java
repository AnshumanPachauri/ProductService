package com.ap.microservices.product;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.mongodb.MongoDBContainer;

import io.restassured.RestAssured;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {
	
	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");
	
	@LocalServerPort
	private Integer port;
	
	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}
	
	static{
		mongoDBContainer.start();
	}
	
	@Test
	void shouldCreateProduct() {
		
		String requestBody = """
            {
                "name":"Iphone 17",
                "description":"Latest Iphone from apple",
                "price":"150000"
            }
            """;
		
		RestAssured.given().contentType("application/json").body(requestBody)
		.when().post("/api/product").then().statusCode(201)
		.body("id", Matchers.notNullValue())
		.body("name", Matchers.equalTo("Iphone 17"))
		.body("description", Matchers.equalTo("Latest Iphone from apple"))
		.body("price", Matchers.equalTo("150000"));
		
	}

}
