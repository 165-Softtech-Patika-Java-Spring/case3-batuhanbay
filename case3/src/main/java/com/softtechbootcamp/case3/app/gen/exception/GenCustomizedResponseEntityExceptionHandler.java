package com.softtechbootcamp.case3.app.gen.exception;

import com.softtechbootcamp.case3.app.gen.dto.GeneralResponse;
import com.softtechbootcamp.case3.app.gen.exceptions.DoesNotExistExceptions;
import com.softtechbootcamp.case3.app.gen.exceptions.DuplicateEntityExceptions;
import com.softtechbootcamp.case3.app.gen.exceptions.EntityNotFoundExceptions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class GenCustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest){

        Date errorDate = new Date();
        String message = ex.getMessage();
        String description = webRequest.getDescription(false);

        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate, message, description, HttpStatus.INTERNAL_SERVER_ERROR.value());

        GeneralResponse<GenExceptionResponse> generalResponse = GeneralResponse.error(genExceptionResponse);
        generalResponse.setMessages(message);

        return new ResponseEntity<>(generalResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleDoesNotExistExceptions(DoesNotExistExceptions ex){
        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String detailMessage = ex.getBaseErrorMessage().getDetailMessage();
        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate,message,detailMessage,HttpStatus.NOT_ACCEPTABLE.value());
        GeneralResponse<GenExceptionResponse> generalResponse = GeneralResponse.error(genExceptionResponse);
        generalResponse.setMessages(detailMessage);
        return new ResponseEntity<>(generalResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleDuplicateEntityExceptions(DuplicateEntityExceptions ex){
        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String detailMessage = ex.getBaseErrorMessage().getDetailMessage();
        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate,message,detailMessage,HttpStatus.CONFLICT.value());
        GeneralResponse<GenExceptionResponse> generalResponse = GeneralResponse.error(genExceptionResponse);
        generalResponse.setMessages(detailMessage);
        return new ResponseEntity<>(generalResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public final  ResponseEntity<Object> handleEntityNotFoundExceptions(EntityNotFoundExceptions ex){
        GenExceptionResponse genExceptionResponse = new GenExceptionResponse();

        Date errorDate = new Date();

        if (ex.getGenericErrorMessage() != null){
            genExceptionResponse.setMessage(ex.getGenericErrorMessage());
            genExceptionResponse.setErrorDate(errorDate);
            genExceptionResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
        }else{
            genExceptionResponse.setErrorDate(errorDate);
            genExceptionResponse.setMessage(ex.getBaseErrorMessage().getMessage());
            genExceptionResponse.setDetailMessage(ex.getBaseErrorMessage().getDetailMessage());
            genExceptionResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
        }

        GeneralResponse<GenExceptionResponse> generalResponse = GeneralResponse.error(genExceptionResponse);
        if (ex.getGenericErrorMessage() == null){
            generalResponse.setMessages(ex.getBaseErrorMessage().getErrorCode());
        }else{
            generalResponse.setMessages(genExceptionResponse.getMessage());
        }

        return new ResponseEntity<>(generalResponse, HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Date errorDate = new Date();
        String message = new StringBuilder(ex.getBindingResult().getFieldError().getField()).append(" field not valid").toString();
        String detailMessage = ex.getBindingResult().getFieldError().getDefaultMessage();

        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate,message,detailMessage,status.value());
        GeneralResponse<GenExceptionResponse> restResponse = GeneralResponse.error(genExceptionResponse);
        restResponse.setMessages(message);

        return new ResponseEntity<>(restResponse, HttpStatus.BAD_REQUEST);
    }
}
