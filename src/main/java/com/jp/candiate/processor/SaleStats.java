package com.jp.candiate.processor;

import com.jp.canidate.messages.SaleDetails;
import com.jp.canidate.messages.SaleDetailsAdjustment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by anhtrang on 26/1/18.
 */
public class SaleStats {

    private  List<SaleDetails> numberOfSaleDetails = new ArrayList<SaleDetails>();
    private  List<SaleDetailsAdjustment> saleDetailsAdjustments = new ArrayList<SaleDetailsAdjustment>();
    private  Map<String,SaleDetailReport> saleAggregator = new ConcurrentHashMap<String, SaleDetailReport>();
    private  List<SaleAdjustmentReport> saleAdjustmentReports = new ArrayList<SaleAdjustmentReport>();

    public Map<String, SaleDetailReport>  calulateSales(){

        //  calculate the total of sale detal
        for(SaleDetails saleDetails : numberOfSaleDetails){

            String productType  = saleDetails.getProductType();
             if(saleAggregator.containsKey(productType)){
                 SaleDetailReport saleDetailReport=  saleAggregator.get(productType);
                 saleDetailReport.setTotalValue(saleDetailReport.getTotalValue() + saleDetails.getValue());
                 saleDetailReport.setNumberOfSale(saleDetailReport.getNumberOfSale() + saleDetails.getNumber());

             } else{

                SaleDetailReport saleDetailReport = new SaleDetailReport(productType,saleDetails.getNumber(), saleDetails.getValue());
                saleAggregator.put(productType, saleDetailReport);
            }
        }

        return saleAggregator;


    }

    public  List<SaleAdjustmentReport> calculteAdjustment(){
        for (SaleDetailsAdjustment saleDetailsAdjustment : saleDetailsAdjustments){
             switch (saleDetailsAdjustment.getAdjustmentType()){
                 case ADD:
                     calculateAddAdjustment(saleDetailsAdjustment);
                     break;
                 case MUL:
                     calculateMuptileAdjustment(saleDetailsAdjustment);
                     break;
                 case SUB:
                     calculateSubAdjustment(saleDetailsAdjustment);
                     break;
                 default:

             }
        }
        return saleAdjustmentReports;
    }

    private List<SaleAdjustmentReport> calculateSubAdjustment(SaleDetailsAdjustment saleDetailsAdjustment) {
        SaleAdjustmentReport saleAdjustmentReport = new SaleAdjustmentReportBuilder().createSaleAdjustmentReport();
        saleAdjustmentReport.setAdjustmentType(saleDetailsAdjustment.getAdjustmentType().name());
        saleAdjustmentReport.setProductType(saleDetailsAdjustment.getProductType());
        saleAdjustmentReport
                .setTotalValue(saleAggregator.get(saleDetailsAdjustment.getProductType())
                        .getNumberOfSale() * saleDetailsAdjustment.getAdjustmentAmt()
                        * -1);

        saleAdjustmentReports.add(saleAdjustmentReport);
        return  saleAdjustmentReports;
    }

    private List<SaleAdjustmentReport> calculateMuptileAdjustment(SaleDetailsAdjustment saleDetailsAdjustment) {
        SaleAdjustmentReport saleAdjustmentReport = new SaleAdjustmentReportBuilder().createSaleAdjustmentReport();
        saleAdjustmentReport.setAdjustmentType(saleDetailsAdjustment.getAdjustmentType().name());
        saleAdjustmentReport.setProductType(saleDetailsAdjustment.getProductType());
        saleAdjustmentReport
                .setTotalValue(saleAggregator.get(saleDetailsAdjustment.getProductType())
                                .getNumberOfSale() * saleDetailsAdjustment.getAdjustmentAmt()
                );
        saleAdjustmentReports.add(saleAdjustmentReport);
        return  saleAdjustmentReports;
    }

    private List<SaleAdjustmentReport> calculateAddAdjustment(SaleDetailsAdjustment saleDetailsAdjustment) {
        SaleAdjustmentReport saleAdjustmentReport = new SaleAdjustmentReportBuilder()
                .setAdjustmentType(saleDetailsAdjustment.getAdjustmentType().name())
                .setProductType(saleDetailsAdjustment.getProductType())
                .createSaleAdjustmentReport();

        saleAdjustmentReport
                .setTotalValue(saleAggregator.get(saleDetailsAdjustment.getProductType())
                                .getNumberOfSale() * saleDetailsAdjustment.getAdjustmentAmt()
                );

        saleAdjustmentReports.add(saleAdjustmentReport);
        return  saleAdjustmentReports;

    }

    public List<SaleDetails> getNumberOfSaleDetails() {
        return numberOfSaleDetails;
    }

    public List<SaleDetailsAdjustment> getSaleDetailsAdjustments() {
        return saleDetailsAdjustments;
    }

    public Map<String, SaleDetailReport> getSaleAggregator() {
        return saleAggregator;
    }

    public List<SaleAdjustmentReport> getSaleAdjustmentReports() {
        return saleAdjustmentReports;
    }

    public void setNumberOfSaleDetails(List<SaleDetails> numberOfSaleDetails) {
        this.numberOfSaleDetails = numberOfSaleDetails;
    }

    public void setSaleDetailsAdjustments(List<SaleDetailsAdjustment> saleDetailsAdjustments) {
        this.saleDetailsAdjustments = saleDetailsAdjustments;
    }
}
