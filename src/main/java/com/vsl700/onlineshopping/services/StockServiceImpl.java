package com.vsl700.onlineshopping.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vsl700.onlineshopping.data.StockRepository;
import com.vsl700.onlineshopping.data.models.Stock;

@Service
public class StockServiceImpl implements StockService {

    private static final String IMAGE_CONTENT_TYPE_JPEG = "image/jpeg";
    private static final String IMAGE_CONTENT_TYPE_PNG = "image/png";

    @Autowired
    private StockRepository stockRepo;

    @Autowired
    private FileUploadService fileUploadService;

    @Override
    public Optional<Stock> findStockById(String id) {
        return stockRepo.findById(id);
    }

    @Override
    public void saveStock(String name, String description, MultipartFile imageFile) {
        if(!IMAGE_CONTENT_TYPE_JPEG.equals(imageFile.getContentType())
            && !IMAGE_CONTENT_TYPE_PNG.equals(imageFile.getContentType()))
            throw new IllegalArgumentException("The image was not in the appropriate type! (%s)".formatted(imageFile.getContentType()));
        
        String imageURL = fileUploadService.save(imageFile);

        Stock stock = new Stock(name, description, imageURL);
        stockRepo.save(stock);
    }

    @Override
    public List<Stock> findAllStocks() {
        return stockRepo.findAll();
    }

    @Override
    public List<Stock> findStockByKeyString(String keyString) {
        return findAllStocks().stream().filter(stock -> stock.getName().toLowerCase().contains(keyString.toLowerCase()) || 
                                                        stock.getDescription().toLowerCase().contains(keyString.toLowerCase())).toList();
    }
}
