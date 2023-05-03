package com.vsl700.onlineshopping.data.models;

import java.time.Instant;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Stock {

    @Id
    private String id;
    private String name;
    private String description;
    private String imageLink;
    private Date postingDate;

    public Stock() {
        postingDate = Date.from(Instant.now());
    }

    public Stock(String name, String description, String imageLink) {
        this();
        
        this.name = name;
        this.description = description;
        this.imageLink = imageLink;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    @Override
    public String toString() {
        return "Stock [id=" + id + ", name=" + name + ", description=" + description + ", imageLink=" + imageLink
                + ", postingDate=" + postingDate + "]";
    }
}
