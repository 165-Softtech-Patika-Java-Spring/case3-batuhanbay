package com.softtechbootcamp.case3.app.gen.exceptions;

import com.softtechbootcamp.case3.app.gen.enums.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundExceptions extends BusinessExceptions{

    private String genericErrorMessage;

    public EntityNotFoundExceptions(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }

    public EntityNotFoundExceptions(String genericErrorMessage) {
        this.genericErrorMessage = genericErrorMessage;
    }

    public String getGenericErrorMessage() {
        return genericErrorMessage;
    }

}
