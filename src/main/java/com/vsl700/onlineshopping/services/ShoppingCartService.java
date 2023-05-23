package com.vsl700.onlineshopping.services;

import java.util.List;

import com.vsl700.onlineshopping.data.models.Stock;

public interface ShoppingCartService {
    void addStockToCart(String id, int amount);
    List<Stock> getStocksFromCart();
    void clearShoppingCart();
}
