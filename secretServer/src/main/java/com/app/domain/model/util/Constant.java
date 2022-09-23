
package com.app.domain.model.util;

import  com.app.domain.model.response.Error;
import com.app.domain.model.response.Message;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class Constant {

    //APLICATION-PATHS
    public static final String PATH_USER_LOGIN  = "/api/secret-server/login";
    public static final String PATH_USER_SIGNUP = "/api/secret-server/singUp";


     //APPLICATION-TITLES
     public static final String TITLE_SUCCESSFUL_OPERATION =  "SUCCESS OPERATION";
     public static final String FAILED_OPERATION_TITLE =  "FAILED OPERATION";

     //APPLICATION-DESCRIPTIONS
    public static final String DESCRIPTION_SUCCESSFUL_SIGN_UP =" Successful user signup  ";
    public static final String DESCRIPTION_SUCCESSFUL_LOGIN =" Successful user login  ";
     //ERROR CODES
     public static final String ERROR_MISSING_ARGUMENTS_CODE = "ER-401";
     public static final String ERROR_SIGNUP_USER_CODE = "ER-402";
     //SUCCESS-CODES
     public static final String SUCCESSFUL_SIGNUP_USER_CODE = "800-1";
     public static final String SUCCESSFUL_LOGIN_USER_CODE = "800-2";

    public static final Map<String, Error> errorMessages = new HashMap<>();
    public static final Map<String, Message> successfulMessages = new HashMap<>();

    static {
        errorMessages.put(ERROR_MISSING_ARGUMENTS_CODE,
                new Error(HttpStatus.BAD_REQUEST.value(), ERROR_MISSING_ARGUMENTS_CODE, FAILED_OPERATION_TITLE, "Missing arguments" ));
        errorMessages.put(ERROR_SIGNUP_USER_CODE,
                new Error(HttpStatus.BAD_REQUEST.value(), ERROR_MISSING_ARGUMENTS_CODE, FAILED_OPERATION_TITLE, "Fail create user" ));

       successfulMessages.put(SUCCESSFUL_SIGNUP_USER_CODE,
               new Message(PATH_USER_SIGNUP, DESCRIPTION_SUCCESSFUL_SIGN_UP, TITLE_SUCCESSFUL_OPERATION, 200  ));
        successfulMessages.put(SUCCESSFUL_LOGIN_USER_CODE,
                new Message(PATH_USER_LOGIN, DESCRIPTION_SUCCESSFUL_LOGIN, TITLE_SUCCESSFUL_OPERATION, 200  ));
    }

    public static Error getErrorMessage(String code){

          return errorMessages.get(code);
    }

    public static Message getSuccesMessage(String code){

        return successfulMessages.get(code);
    }
}