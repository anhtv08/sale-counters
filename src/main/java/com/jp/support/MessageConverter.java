package com.jp.support;

import com.jp.candidate.constants.ErrorCodes;
import com.jp.candidate.exceptions.InvalidInputException;
import com.jp.canidate.messages.*;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by anhtrang on 25/1/18.
 */
public class MessageConverter {
     //public static

    // assuming that messeage passed as input string and has following format
    // MESSAGE_TYPE|PRODUCT_TYPE|PRICE|AMOUNT|ADJUSTMENT_TYPE|ADJUSTMENT_AMOUNT
    // WHERE MESSAGE TYPE:
    // 0: SALE MESSAGE ( MESSAGE TYPE 1)
    // 1: DETAIL OF SALE ( MESSAGE TYPE 2)
    // 2:  ADJUSTMENT MESSAGE FOR SALE
    // IN CASE OF MESSAGE TYPE 1: AMOUTN, ADJUSTMENT WILL BE EMPTY
    // MESSAGE TYPE 2: ADJUSTMENT TYPE WILL BE EMPTY
    // MESSAGE TYPE 3: ALL FIELD WILL BE REQUIRED
    // WE NEED TO VALIDATE ANY MESSAGE INPUT BEFORE PROCESSING.
    // MESSAGE ONLY PROCESSED IF IT IS VALUE, OTHERWISE IF WILL BE IGNORED AND LOGGED IN CONSOLE LOG

    //2|banana|10.0||SUB|5
    private static  final String MESSAGE_SPITTER = "\\|";

    //2|apple|10.0||MUL|1.5
    private static  final String ADJUSTMENT_REG_EXP  = "(^2\\|\\w+\\|\\d+.\\d+(\\|){2}((ADD)|(SUB)|(MUL))\\|\\d+.*\\d*$)";
    //0|apple|10.0||||
    private static  final String SALE_REG_EXP        = "(^0\\|\\w+\\|\\d+.\\d+\\|+$)";
    private static  final String SALE_DETAIL_REG_EXP = "(^1\\|\\w+\\|\\d+.\\d+\\|\\d+\\|+$)";

    public static Sale convert(final String productMsg)  {

         Objects.requireNonNull(productMsg,"Message can not be empty");
         String[] messages =  productMsg.split(MESSAGE_SPITTER);

        int messageType = Integer.parseInt(messages[0]);
          Sale sale;
        try {
            switch (messageType){
                case 0: validateMessage(productMsg);
                    break;
                case 1: validateDetailSale(productMsg);
                    break;
                case 2: validateAdjustment(productMsg);
                    break;
            }
//            validateMessage(productMsg);
        } catch (InvalidInputException e) {
             System.out.println(e.getErrorMessage());
        }
        switch (messageType){
              case  0 :
                  sale = createSale(messages);
                   break;
              case 1:
                   sale = createSaleDetail(messages);
                  break;
              case 2:
                  sale = createAdjustmentSale(messages);
                  break;
              default:
                  return  null;
          }


         return sale;
     }

    private static Sale createSaleDetail(String[] messages) {

        SaleDetails productSale = new ProductSaleBuilder()
                                        .setProductType(messages[1])
                                        .setPrice(Double.parseDouble(messages[2]))
                                        .setNumber(Integer.parseInt(messages[3]))
                                        .createProductSale();
        productSale.setMessageType(MessageType.SALE_DETAIL);
        return  productSale;

    }

    private static Sale createSale(String[] messages) {
        Sale sale = new ProductBuilder()
                                .setProductType(messages[1])
                                .setPrice(Double.parseDouble(messages[2]))
                                .createProduct();
        sale.setMessageType(MessageType.SALE);
        return sale;

    }

    private static Sale createAdjustmentSale(String[] messages) {
        SaleDetailsAdjustment saleAdjustment = new SaleAdjustmentBuilder()
                                            .setProductType(messages[1])
                                            .setPrice(Double.parseDouble(messages[2]))
                                            .setAdjustmentType(AdjustmentType.valueOf(messages[4]))
                                            .setAdjustmentAmt(Double.parseDouble(messages[5]))
                                            .createSaleAdjustment();
        saleAdjustment.setMessageType(MessageType.SALE_ADJUSTMENT);
        return saleAdjustment;


    }



    public static void validateMessage(final String inputMessage) throws InvalidInputException {
        Pattern pattern=  Pattern.compile(SALE_REG_EXP);
        Matcher matcher = pattern.matcher(inputMessage);
        if (!matcher.matches()){
            throw new InvalidInputException(ErrorCodes.INVALID_ERROR_CODE, ErrorCodes.INVALID_MESSAGE_FORMAT);
        }
    }
    public static void validateAdjustment(final String inputMessage) throws InvalidInputException {
        Pattern pattern=  Pattern.compile(ADJUSTMENT_REG_EXP);
        Matcher matcher = pattern.matcher(inputMessage);
        if (!matcher.matches()){
            throw new InvalidInputException(ErrorCodes.INVALID_ERROR_CODE, ErrorCodes.INVALID_MESSAGE_FORMAT);
        }
    }
    public static void validateDetailSale(final String inputMessage) throws InvalidInputException {
        Pattern pattern=  Pattern.compile(SALE_DETAIL_REG_EXP);
        Matcher matcher = pattern.matcher(inputMessage);
        if (!matcher.matches()){
            throw new InvalidInputException(ErrorCodes.INVALID_ERROR_CODE, ErrorCodes.INVALID_MESSAGE_FORMAT);
        }
    }

}
