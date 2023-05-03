package com.vsl700.onlineshopping.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsl700.onlineshopping.data.StockRepository;
import com.vsl700.onlineshopping.data.models.Stock;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepo;

    @Override
    public Optional<Stock> findStockById(String id) {
        return stockRepo.findById(id);
    }

    @Override
    public void saveStock(Stock stock) {
        stockRepo.save(stock);
    }

    @Override
    public List<Stock> findAllStocks() {
        return stockRepo.findAll();
    }

    @Override
    public List<Stock> findStockByKeyString(String keyString) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findStockByKeyString'");
    }
}
