package com.vsl700.onlineshopping.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.vsl700.onlineshopping.data.models.Stock;
import com.vsl700.onlineshopping.services.StockService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class StockController {
    
    @Autowired
    private StockService stockService;

    @GetMapping("/")
    public String home(Model model, HttpServletRequest request){
        model.addAttribute("stockList", stockService.findAllStocks());
        return "index";
    }

    @GetMapping("/search")
    public String homeSearchResults(@RequestParam String query, Model model){
        if(query.isBlank())
            return "redirect:/";

        model.addAttribute("stockList", stockService.findStockByKeyString(query));
        return "index";
    }

    @GetMapping("/view/{stockId}")
    public String viewStock(@PathVariable String stockId, Model model){
        model.addAttribute("stock", stockService.findStockById(stockId).orElseThrow());
        return "view";
    }

    @GetMapping("/create")
    public String newStockView(){
        return "createStock";
    }

    @PostMapping("/create")
    public String newStockView(@RequestParam String name, @RequestParam String description, @RequestParam MultipartFile imageFile){
        stockService.saveStock(name, description, imageFile);
        return "redirect:/";
    }

    @GetMapping("/addToCart")
    public String addToCart(){
        return "redirect:/";
    }

    @GetMapping("/purchase")
    public String purchase(){
        return "redirect:/";
    }


    // TODO Move the following methods to an API controller
    @GetMapping("/api/stocks")
    @ResponseBody
    public List<Stock> getAllStocks(){
        return stockService.findAllStocks();
    }

    @PostMapping("/api/stock/new")
    @ResponseBody
    public void newStock(@RequestBody Stock stock){
        //stockService.saveStock(stock);
    }

    // TODO Move the following methods to a Unit test
    @GetMapping("/api/tests/myCookies")
    @ResponseBody
    public String readServletCookie(HttpServletRequest request, String name){
        if(request.getCookies() == null)
            return null;

        return Arrays.stream(request.getCookies())
          .filter(cookie->name.equals(cookie.getName()))
          .map(Cookie::getValue)
          .findAny().get();
    }

}
