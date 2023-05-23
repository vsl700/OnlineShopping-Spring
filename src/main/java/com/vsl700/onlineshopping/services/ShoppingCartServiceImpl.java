package com.vsl700.onlineshopping.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.vsl700.onlineshopping.data.ShoppingCartRecRepository;
import com.vsl700.onlineshopping.data.UserRepository;
import com.vsl700.onlineshopping.data.models.ShoppingCartRec;
import com.vsl700.onlineshopping.data.models.Stock;
import com.vsl700.onlineshopping.data.models.User;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private StockService stockService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ShoppingCartRecRepository shoppingCartRepo;

    @Override
    public void addStockToCart(String id, int amount) {
        Stock stock = stockService.findStockById(id).get();
        ShoppingCartRec shoppingCartRec = shoppingCartRepo.findByStock(stock).orElse(null);
        if(shoppingCartRec != null){ // If the user has already ordered such a product we simply increment its amount in the shopping cart record
            shoppingCartRec.setAmount(shoppingCartRec.getAmount() + amount);
            shoppingCartRepo.save(shoppingCartRec);
            return;
        }

        User user = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        shoppingCartRec = new ShoppingCartRec(user, stock, amount);
        shoppingCartRec = shoppingCartRepo.save(shoppingCartRec);

        user.getShoppingCart().add(shoppingCartRec);
        userRepo.save(user);
    }
    
    @Override
    public List<Stock> getStocksFromCart() {
        User user = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        return shoppingCartRepo.findAllByUser(user).stream().map(cartRec -> cartRec.getStock()).toList();
    }

    @Override
    public void clearShoppingCart() {
        User user = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        shoppingCartRepo.findAllByUser(user).forEach(scr -> shoppingCartRepo.delete(scr));
        
        user.getShoppingCart().clear();
        userRepo.save(user);
    }
    
}
