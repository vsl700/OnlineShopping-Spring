package com.vsl700.onlineshopping.services;

import java.util.List;
import java.util.Optional;

import com.vsl700.onlineshopping.data.models.Stock;

public interface StockService {
    
    void saveStock(Stock stock);
    Optional<Stock> findStockById(String id);
    List<Stock> findAllStocks();
    /**
     * This function will simply implement the search function of the web application
     * @param keyString - the text entered on the search bar
     * @return the stocks found with the key string
     */
    List<Stock> findStockByKeyString(String keyString);

}
