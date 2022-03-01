package com.softtechbootcamp.case3.app.gen.exceptions;

import com.softtechbootcamp.case3.app.gen.enums.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class DoesNotExistExceptions extends BusinessExceptions{

    public DoesNotExistExceptions(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }

}
