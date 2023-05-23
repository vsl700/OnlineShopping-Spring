package com.vsl700.onlineshopping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vsl700.onlineshopping.services.ShoppingCartService;

@Controller
public class CartController {
    
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/cart")
    public String cartView(Model model){
        model.addAttribute("stockList", shoppingCartService.getStocksFromCart());
        return "shoppingCart";
    }

    @GetMapping("/cart/clear")
    public String cartClear(){
        shoppingCartService.clearShoppingCart();
        return "redirect:/cart";
    }

    @PostMapping("cart/add/{stockId}")
    public String addToCart(@PathVariable String stockId, @RequestParam int amount){
        shoppingCartService.addStockToCart(stockId, amount);
        return "redirect:/";
    }

}
