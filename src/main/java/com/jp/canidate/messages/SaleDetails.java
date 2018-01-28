package com.jp.canidate.messages;

/**
 * Created by anhtrang on 25/1/18.
 */
public class SaleDetails extends Sale {
    private  int number;

    public SaleDetails(String productType, double price, int number) {
        super(productType, price);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public  double getValue(){
         return  number * getPrice();
    }

}
