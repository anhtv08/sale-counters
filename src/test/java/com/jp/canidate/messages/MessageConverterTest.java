package com.jp.canidate.messages;

import com.jp.candidate.exceptions.InvalidInputException;
import com.jp.support.MessageConverter;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by anhtrang on 27/1/18.
 */
public class MessageConverterTest {

    @Test
    public void testConvert() throws Exception {

        List<String> testInput = Files.readAllLines(Paths.get(ClassLoader.getSystemResource("input1").toURI()));
        List<Sale> sales = testInput.stream().map(MessageConverter::convert).collect(Collectors.toList());

        Assert.assertEquals("test number product type of apple", sales.stream().filter(item-> "apple".equals(item.getProductType())).count(), 7 );
        Assert.assertEquals("test number product type of banana", sales.stream().filter(item-> "banana".equals(item.getProductType())).count(), 6);

        Assert.assertEquals("test number of adjustment message", sales.stream().filter(item-> MessageType.SALE_ADJUSTMENT.equals(item.getMessageType())).count(), 5 );
        Assert.assertEquals("test number of adjustment message", sales.stream().filter(item-> MessageType.SALE.equals(item.getMessageType())).count(), 4 );
        Assert.assertEquals("test number of adjustment message", sales.stream().filter(item-> MessageType.SALE_DETAIL.equals(item.getMessageType())).count(), 4);

    }

    @Test(expected = InvalidInputException.class)
    public void testInvalidMessage() throws Exception {

        String inputTest = "10|apple|10.0||||";
        for(String input : inputTest.split("\n")) {
            MessageConverter.validateMessage(input);
        }

    }
    @Test
    public void testValidMessage() throws Exception {

        String testInput = "0|apple|10.0||||";
//        for(String input : testInput) {
            MessageConverter.validateMessage(testInput);
//        }

    }

    @Test
    public void testAdjustmentMessage() throws Exception {

        String testInput  = "2|apple|10.0||ADD|15\n" +
                "2|apple|10.0||SUB|5\n" +
                "2|apple|10.0||MUL|1.5";

        for(String input : testInput.split("\n")) {
            MessageConverter.validateAdjustment(input);
        }

    }
    @Test(expected = InvalidInputException.class)
    public void testInvalidAdjustmentMessage() throws Exception {

        String testInput  = "0|apple|10.0||TEST|15";

        for(String input : testInput.split("\n")) {
            MessageConverter.validateAdjustment(input);
        }

    }

    @Test
    public void testSaleDetailMessage() throws Exception {

        String testInput  = "1|apple|10.0|5||";

        for(String input : testInput.split("\n")) {
            MessageConverter.validateDetailSale(input);
        }

    }
    @Test(expected = InvalidInputException.class)
    public void testInvalidSaleDetailMessage() throws Exception {

        String testInput  = "1|apple|10.0|test||";
        for(String input : testInput.split("\n")) {
            MessageConverter.validateDetailSale(input);
        }

    }


}