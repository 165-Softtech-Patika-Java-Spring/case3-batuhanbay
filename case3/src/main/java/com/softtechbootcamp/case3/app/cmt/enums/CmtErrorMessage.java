package com.softtechbootcamp.case3.app.cmt.enums;

import com.softtechbootcamp.case3.app.gen.enums.BaseErrorMessage;

public enum CmtErrorMessage implements BaseErrorMessage {
    ;

    private  final String message;
    private  final String detailMessage;
    private  final String errorCode;

    CmtErrorMessage(String message, String detailMessage, String errorCode){
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
