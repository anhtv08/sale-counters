package com.jp.canidate.messages;

/**
 * Created by anhtrang on 25/1/18.
 */
public enum AdjustmentType {
    ADD("ADD"), SUB("SUB"), MUL("MUL");
    private  String value;

    AdjustmentType(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
