package com.jp.canidate.messages;

public class ProductSaleBuilder {
    private String productType;
    private double price;
    private int number;

    public ProductSaleBuilder setProductType(String productType) {
        this.productType = productType;
        return this;
    }

    public ProductSaleBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public ProductSaleBuilder setNumber(int number) {
        this.number = number;
        return this;
    }

    public SaleDetails createProductSale() {
        return new SaleDetails(productType, price, number);
    }
}