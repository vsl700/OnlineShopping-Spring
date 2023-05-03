package com.vsl700.onlineshopping.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vsl700.onlineshopping.data.models.Stock;

public interface StockRepository extends MongoRepository<Stock, String> {
    
}
