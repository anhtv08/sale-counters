package com.jp.candiate.processor;

import com.jp.canidate.messages.Sale;
import com.jp.canidate.messages.SaleDetails;
import com.jp.canidate.messages.SaleDetailsAdjustment;
import com.jp.support.MessageConverter;
import junit.framework.Assert;
import org.junit.Before;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by anhtrang on 27/1/18.
 */
public class SaleStatsTest {
    SaleStats saleStats;
    @Before
    public void setup() throws URISyntaxException, IOException {
        saleStats = new SaleStats();
        List<String> testInput = Files.readAllLines(Paths.get(ClassLoader.getSystemResource("apple.data").toURI()));
        List<Sale> sales = testInput.stream().map(MessageConverter::convert).collect(Collectors.toList());

        List<SaleDetails> saleDetailses = sales.stream().map(sale -> {
            SaleDetails saleDetails = null;
            switch (sale.getMessageType()) {
                case SALE:
                    saleDetails = new SaleDetails(sale.getProductType(), sale.getPrice(), 1);
                    break;
                case SALE_DETAIL:
                    saleDetails = (SaleDetails) sale;
                    break;
                default:


            }
            return saleDetails;

        }).filter(item -> item != null).collect(Collectors.toList());

        saleStats.setNumberOfSaleDetails(saleDetailses);

//        saleStats.calulateSales();

    }
    @org.junit.Test
    public void testCalculateSales() throws Exception {

        saleStats.calulateSales();
        Map<String,SaleDetailReport>  saleDetailReportMap = saleStats.getSaleAggregator();

        SaleDetailReport actualAppleSaleReport = saleDetailReportMap.get("apple");

        SaleDetailReport expectedSaleDetailReport = new SaleDetailReport("apple", 13, 130);

        Assert.assertEquals("asserting apple sale report :", actualAppleSaleReport, expectedSaleDetailReport);

        SaleDetailReport actualBananaSaleReport = saleDetailReportMap.get("banana");

        SaleDetailReport expectedBananaSaleDetailReport = new SaleDetailReport("banana", 13, 130);

        Assert.assertEquals("asserting banana sale report :", actualBananaSaleReport, expectedBananaSaleDetailReport);

    }

    @org.junit.Test
    public void testCalculteAdjustment() throws Exception {
        List<String> testInput = Files.readAllLines(Paths.get(ClassLoader.getSystemResource("apple.data").toURI()));
        List<Sale> sales = testInput.stream().map(MessageConverter::convert).collect(Collectors.toList());


        List<SaleDetails> saleDetailses = sales.stream().map(sale -> {
            SaleDetails saleDetails = null;
            switch (sale.getMessageType()) {
                case SALE:
                    saleDetails = new SaleDetails(sale.getProductType(), sale.getPrice(), 1);
                    break;
                case SALE_DETAIL:
                    saleDetails = (SaleDetails) sale;
                    break;
                default:


            }
            return saleDetails;

        }).filter(item -> item != null).collect(Collectors.toList());

        saleStats.setNumberOfSaleDetails(saleDetailses);
        saleStats.calulateSales();


        List<SaleDetailsAdjustment> saleDetailsAdjustments  = sales.stream().map(sale -> {
            SaleDetailsAdjustment saleDetailsAdjustment = null;
            switch (sale.getMessageType()) {
                case SALE_ADJUSTMENT:
                    saleDetailsAdjustment = (SaleDetailsAdjustment)sale;
                    break;
                default:


            }
            return saleDetailsAdjustment;

        }).filter(item -> item != null).collect(Collectors.toList());

        saleStats.setSaleDetailsAdjustments(saleDetailsAdjustments);

        saleStats.calculteAdjustment();
        List<SaleAdjustmentReport> saleAdjustmentReports = saleStats.getSaleAdjustmentReports();
        SaleAdjustmentReport expectedSaleAdjustmentReport = new SaleAdjustmentReportBuilder()
                .setAdjustmentType("ADD")
                .setProductType("apple")
                .setTotalValue(195)
                .createSaleAdjustmentReport();

        assertEquals("test sale adjustment report ", expectedSaleAdjustmentReport, saleAdjustmentReports.get(0));

    }
}