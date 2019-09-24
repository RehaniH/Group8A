package com.example.group8a;

import com.google.firebase.database.Exclude;

public class Product {

    private String name;
    private String color;
    private String category;
    private int quantity;
    private String mUri;

    private String imageKey;



    public Product() {
    }

    public String getmUri() {
        return mUri;
    }

    public void setmUri(String mUri) {
        this.mUri = mUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Exclude
    public String getImageKey() {
        return imageKey;
    }

    @Exclude
    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }
}
