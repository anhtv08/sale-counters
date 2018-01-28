package com.jp.canidate.messages;

/**
 * Created by anhtrang on 25/1/18.
 */
public enum  MessageType {

    SALE(0), SALE_DETAIL(1), SALE_ADJUSTMENT(2);

    private int messageType;
    MessageType(int messageType) {
        this.messageType = messageType;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }
}

