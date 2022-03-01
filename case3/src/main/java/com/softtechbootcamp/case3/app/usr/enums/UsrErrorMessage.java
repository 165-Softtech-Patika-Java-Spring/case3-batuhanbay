package com.softtechbootcamp.case3.app.usr.enums;

import com.softtechbootcamp.case3.app.gen.enums.BaseErrorMessage;

public enum UsrErrorMessage implements BaseErrorMessage {
    USER_NOT_FOUND_USERNAME("Entities Could Not Found", "There are not any saved user with this username.", "case3-100-404"),
    USER_NOT_FOUND_ID("Entities Could Not Found", "There are not any saved user with this id.", "case3-101-404"),
    HAS_DUPLICATE_USER_USERNAME("Encounter A Conflict", "This name is already used for another user.", "case3-200-409"),
    HAS_DUPLICATE_USER_EMAIL("Encounter A Conflict", "This email is already used for another user.", "case3-201-409"),
    HAS_DUPLICATE_USER_PHONE_NUMBER("Encounter A Conflict", "This phone number is already used for another user.", "case3-203-409"),
    HAS_BLANK_USERNAME_PARAMETER("Encounter a Blank Parameter", "Username parameter can not be blank", "case3-203-400"),;

    private  final String message;
    private  final String detailMessage;
    private  final String errorCode;

    UsrErrorMessage(String message, String detailMessage, String errorCode){
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

    public String toString(){
        return this.message;
    }

}
