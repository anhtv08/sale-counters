package com.jp.canidate.messages;

/**
 * Created by anhtrang on 25/1/18.
 */
public class SaleDetailsAdjustment extends SaleDetails {

    private  AdjustmentType adjustmentType;
    private  double adjustmentAmt;

    public SaleDetailsAdjustment(String productType, double price, int number, AdjustmentType adjustmentType, double adjustmentAmt) {
        super(productType, price, number);
        this.adjustmentType = adjustmentType;
        this.adjustmentAmt = adjustmentAmt;
    }

    public AdjustmentType getAdjustmentType() {
        return adjustmentType;
    }

    public double getAdjustmentAmt() {
        return adjustmentAmt;
    }

}

