package com.softtechbootcamp.case3.app.prd.enums;

import com.softtechbootcamp.case3.app.gen.enums.BaseErrorMessage;

public enum PrdErrorMessage implements BaseErrorMessage {
    PRODUCT_NOT_FOUND_ID("Entities Could Not Found", "There are not any saved product with this id.", "case3-001-404"),
    HAS_DUPLICATE_PRODUCT_NAME("Encounter A Conflict", "This name is already used for another product.", "case3-100-409"),
    HAS_DUPLICATE_PRODUCT_NAME_AND_PRICE("Encounter A Conflict", "There is  already saved product with this name and price!", "case3-100-409"),
    ;

    private  final String message;
    private  final String detailMessage;
    private  final String errorCode;

    PrdErrorMessage(String message, String detailMessage, String errorCode){
        this.message = message;
        this.detailMessage = detailMessage;
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getDetailMessage() {
        return detailMessage;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }
}
