package com.softtechbootcamp.case3.app.gen.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenExceptionResponse {
    private Date errorDate;
    private String message;
    private String detailMessage;
    private int errorCode;
}
