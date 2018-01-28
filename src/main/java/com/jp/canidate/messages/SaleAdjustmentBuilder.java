package com.jp.canidate.messages;

public class SaleAdjustmentBuilder {
    private String productType;
    private double price;
    private int number;
    private AdjustmentType adjustmentType;
    private double adjustmentAmt;

    public SaleAdjustmentBuilder setProductType(String productType) {
        this.productType = productType;
        return this;
    }

    public SaleAdjustmentBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public SaleAdjustmentBuilder setNumber(int number) {
        this.number = number;
        return this;
    }

    public SaleAdjustmentBuilder setAdjustmentType(AdjustmentType adjustmentType) {
        this.adjustmentType = adjustmentType;
        return this;
    }

    public SaleAdjustmentBuilder setAdjustmentAmt( double adjustmentAmt) {
        this.adjustmentAmt = adjustmentAmt;
        return this;
    }

    public SaleDetailsAdjustment createSaleAdjustment() {
        return new SaleDetailsAdjustment(productType, price, number, adjustmentType, adjustmentAmt);
    }
}