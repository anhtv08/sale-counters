package com.jp.candiate.processor;

/**
 * Created by anhtrang on 26/1/18.
 */
public class SaleDetailReport {
    private String productType;
    private int numberOfSale;
    private double totalValue;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getNumberOfSale() {
        return numberOfSale;
    }

    public void setNumberOfSale(int numberOfSale) {
        this.numberOfSale = numberOfSale;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public SaleDetailReport(String productType, int numberOfSale, double totalValue) {
        this.productType = productType;
        this.numberOfSale = numberOfSale;
        this.totalValue = totalValue;
    }

    @Override
    public String toString() {
        return "SaleDetailReport{" +
                "productType='" + productType + '\'' +
                ", numberOfSale=" + numberOfSale +
                ", totalValue=" + totalValue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SaleDetailReport that = (SaleDetailReport) o;

        if (numberOfSale != that.numberOfSale) return false;
        if (Double.compare(that.totalValue, totalValue) != 0) return false;
        return !(productType != null ? !productType.equals(that.productType) : that.productType != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = productType != null ? productType.hashCode() : 0;
        result = 31 * result + numberOfSale;
        temp = Double.doubleToLongBits(totalValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

