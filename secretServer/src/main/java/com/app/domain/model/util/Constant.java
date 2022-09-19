
package com.app.domain.model.util;

import  com.app.domain.model.response.Error;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class Constant {

     public static final String SUCCESSFUL_OPERATION_TITLE =  "SUCCESS OPERATION";
     public static final String FAILED_OPERATION_TITLE =  "FAILED OPERATION";

     //ERROR VALIDATION CODE
     public static final String MISSING_ARGUMENTS_CODE = "ER401";

     //ERROR VALIDATION DESCRIPTION
    public static final String MISSING_ARGUMENTS_DESCRIPTION = "Forget arguments";


    public static final Map<String, Error> errorMessages = new HashMap<>();

    static {
        errorMessages.put(MISSING_ARGUMENTS_CODE,
                new Error(HttpStatus.BAD_REQUEST.value(),MISSING_ARGUMENTS_CODE, FAILED_OPERATION_TITLE, MISSING_ARGUMENTS_CODE  ));
    }

    public static Error getErrorMessage(String code){

          return errorMessages.get(code);
    }
}
