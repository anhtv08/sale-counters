package com.jp.candiate.processor;

/**
 * Created by anhtrang on 26/1/18.
 */
public class SaleAdjustmentReport {
    private  String productType;
    private  String adjustmentType;
    private  double totalValue;


    public SaleAdjustmentReport(String productType, String adjustmentType, double totalValue) {
        this.productType = productType;
        this.adjustmentType = adjustmentType;
        this.totalValue = totalValue;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(String adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    @Override
    public String toString() {
        return "SaleAdjustmentReport{" +
                "totalValue=" + totalValue +
                ", adjustmentType='" + adjustmentType + '\'' +
                ", productType='" + productType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SaleAdjustmentReport that = (SaleAdjustmentReport) o;

        if (Double.compare(that.totalValue, totalValue) != 0) return false;
        if (productType != null ? !productType.equals(that.productType) : that.productType != null) return false;
        return !(adjustmentType != null ? !adjustmentType.equals(that.adjustmentType) : that.adjustmentType != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = productType != null ? productType.hashCode() : 0;
        result = 31 * result + (adjustmentType != null ? adjustmentType.hashCode() : 0);
        temp = Double.doubleToLongBits(totalValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
