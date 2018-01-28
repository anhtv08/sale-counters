package com.jp.candidate.exceptions;

/**
 * Created by anhtrang on 27/1/18.
 */
public class InvalidInputException extends Exception {
    private String errorCode;
    private  String errorMessage;


    public InvalidInputException(Throwable cause, String errorCode, String errorMessage) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public InvalidInputException(String invalidErrorCode, String invalidMessageFormat) {
        super();
        this.errorCode = invalidErrorCode;
        this.errorMessage = invalidMessageFormat;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
