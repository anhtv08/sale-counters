package com.jp.canidate.messages;

public class ProductBuilder {
    private String productType;
    private double price;


    public ProductBuilder setProductType(String productType) {
        this.productType = productType;
        return this;
    }

    public ProductBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public Sale createProduct() {
        return new Sale(productType, price);
    }
}