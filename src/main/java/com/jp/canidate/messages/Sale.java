package com.jp.canidate.messages;

/**
 * Created by anhtrang on 25/1/18.
 */
public class Sale  {
    private  String productType;
    private  double  price;
    private MessageType messageType;

    public Sale(String productType, double price) {
        this.productType = productType;
        this.price = price;
    }

    public String getProductType() {
        return productType;
    }

    public double getPrice() {
        return price;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sale sale = (Sale) o;

        if (Double.compare(sale.price, price) != 0) return false;
        if (!productType.equals(sale.productType)) return false;
        return messageType == sale.messageType;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = productType.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + messageType.hashCode();
        return result;
    }
}
