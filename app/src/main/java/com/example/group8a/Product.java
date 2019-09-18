package com.example.group8a;

public class Product {

    private String name;
    private String color;
    private String category;
    private int quantity;
    private String mUri;
    private String imageData;

    public Product() {
    }

    public String getImageUri() {
        return mUri;
    }

    public void setImageUri(String imageUri) {
        this.mUri = imageUri;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
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
}
