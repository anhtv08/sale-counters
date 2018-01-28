package com.jp.candiate.processor;

public class SaleAdjustmentReportBuilder {
    private String productType;
    private String adjustmentType;
    private double totalValue;

    public SaleAdjustmentReportBuilder setProductType(String productType) {
        this.productType = productType;
        return this;
    }

    public SaleAdjustmentReportBuilder setAdjustmentType(String adjustmentType) {
        this.adjustmentType = adjustmentType;
        return this;
    }

    public SaleAdjustmentReportBuilder setTotalValue(double totalValue) {
        this.totalValue = totalValue;
        return this;
    }

    public SaleAdjustmentReport createSaleAdjustmentReport() {
        return new SaleAdjustmentReport(productType, adjustmentType, totalValue);
    }
}