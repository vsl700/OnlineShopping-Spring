package com.vsl700.onlineshopping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vsl700.onlineshopping.services.StockService;

@Controller
public class PurchaseController {
    
    @Autowired
    private StockService stockService;


    @PostMapping("/purchase/{stockId}")
    public String purchase(@PathVariable String stockId, @RequestParam int amount){
        stockService.purchaseSingleItem(stockId, amount);

        return "redirect:/";
    }

    @GetMapping("/purchase")
    public String purchaseAll(){
        stockService.purchaseAllFromCart();

        return "redirect:/";
    }
}
