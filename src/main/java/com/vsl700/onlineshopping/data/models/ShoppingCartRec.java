package com.vsl700.onlineshopping.data.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document("shopping_cart")
public class ShoppingCartRec {
    @Id
    private String id;
    @DocumentReference
    private User user;
    @DocumentReference
    private Stock stock;
    private int amount;

    public ShoppingCartRec() {}

    public ShoppingCartRec(User user, Stock stock, int amount) {
        this.user = user;
        this.stock = stock;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
