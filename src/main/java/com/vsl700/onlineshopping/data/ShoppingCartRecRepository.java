package com.vsl700.onlineshopping.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vsl700.onlineshopping.data.models.ShoppingCartRec;
import com.vsl700.onlineshopping.data.models.Stock;
import com.vsl700.onlineshopping.data.models.User;

public interface ShoppingCartRecRepository extends MongoRepository<ShoppingCartRec, String> {
    List<ShoppingCartRec> findAllByUser(User user);
    Optional<ShoppingCartRec> findByStock(Stock stock);
}
