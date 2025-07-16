package com.incubyte.sweetshop;

public class Sweet {
    private String id;
    private String name;
    private String category;
    private double price;
    private int quantity;

    public Sweet(String id, String name, String category, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    //  Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    //  Setters (needed for update operations like purchase/restock)
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
