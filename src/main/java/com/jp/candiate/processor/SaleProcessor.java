package com.jp.candiate.processor;

import com.jp.support.MessageConverter;
import com.jp.canidate.messages.Sale;
import com.jp.canidate.messages.SaleDetails;
import com.jp.canidate.messages.SaleDetailsAdjustment;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by anhtrang on 26/1/18.
 */
public class SaleProcessor {

    private int counter;
    private SaleStats saleStats;

    private final static int SLEEP_TIME = 5 ; // pause for 5 seconds to show adjustment report

    public SaleProcessor(SaleStats saleStats) {
        this.saleStats = saleStats;
    }

    public SaleStats getSaleStats() {
        return saleStats;
    }

    public void setSaleStats(SaleStats saleStats) {
        this.saleStats = saleStats;
    }

    public void processSale(String inputMessage) throws InterruptedException {

        counter++;

        System.out.println("number of processed message :" + counter);

        if (counter%10 == 0){

            System.out.println("******* START showing sale report **********");
            showSaleReport();
            System.out.println("******* END showing sale report **********");
        }
        if (counter%50 == 0) {
            Thread.sleep(SLEEP_TIME * 1000);
            showSaleAdjustmentReport();
            showSaleReport();
            //wait();
            //wait();
        } else {
            processSaleDetails(inputMessage);
            processSalesAdjustment(inputMessage);
        }

    }

    private void showSaleReport(){
        saleStats.calulateSales();

        for (Map.Entry<String, SaleDetailReport> entry : saleStats.getSaleAggregator().entrySet()){
            System.out.println(entry.getValue());
        }
    }
    private void showSaleAdjustmentReport(){

        System.out.println("*******START showing adjustment report********");
        saleStats.calculteAdjustment();
        for(SaleAdjustmentReport saleAdjustmentReport: saleStats.getSaleAdjustmentReports()){
            System.out.println(saleAdjustmentReport);
        }

        System.out.println("*******END showing adjustment report********");

    }

    private void processSalesAdjustment(String inputMessage) {

        Sale sale = MessageConverter.convert(inputMessage);

        switch (sale.getMessageType()){

            case SALE_ADJUSTMENT:
                saleStats.getSaleDetailsAdjustments().add((SaleDetailsAdjustment)sale);
                break;
            default:

        }

    }

    private void processSaleDetails(String inputMessage) {
        Sale sale = MessageConverter.convert(inputMessage);

        switch (sale.getMessageType()){
            case SALE:
                SaleDetails saleDetails = new SaleDetails(sale.getProductType(), sale.getPrice(), 1);
                saleStats.getNumberOfSaleDetails().add(saleDetails);
                break;
            case SALE_DETAIL:
                saleStats.getNumberOfSaleDetails().add((SaleDetails)sale);
                break;
            default:

        }

    }

    public  static void main(String[] args) throws Exception {
        SaleStats saleStats = new SaleStats();

        List<String> inputMessage = Files.readAllLines(Paths.get(args[0]));


        SaleProcessor saleProcessor = new SaleProcessor(saleStats);

        for(String saleItem : inputMessage){
            saleProcessor.processSale(saleItem);
        }

    }

}
