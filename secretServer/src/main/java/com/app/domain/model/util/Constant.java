
package com.app.domain.model.util;

import  com.app.domain.model.response.Error;
import com.app.domain.model.response.Message;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class Constant {

    //APPLICATION-PATHS
    public static final String PATH_USER_LOGIN     = "/api/secret-server/signIn";
    public static final String PATH_USER_SIGNUP    = "/api/secret-server/signUp";
    public static final String PATH_VALIDATE_TOKEN = "/api/secret-server/validate/token";


     //APPLICATION-TITLES
     public static final String TITLE_SUCCESSFUL_OPERATION =  "SUCCESS OPERATION";
     public static final String FAILED_OPERATION_TITLE =  "FAILED OPERATION";

     //APPLICATION-DESCRIPTIONS
    public static final String DESCRIPTION_SUCCESSFUL_SIGN_UP =" Successful user signup  ";
    public static final String DESCRIPTION_SUCCESSFUL_LOGIN =" Successful user login  ";
     //ERROR CODES
     public static final String ERROR_MISSING_ARGUMENTS_CODE = "ER-401";
     public static final String ERROR_SIGNUP_USER_CODE = "ER-402";
     public static final String ERROR_LOGIN_USER_CODE  = "ER-403";
     public static final String ERROR_LOGIN_BY_TOKEN_CODE  = "ER-404";

    public static final String ERROR_REMOVE_USER = "ER-405";

     //SUCCESS-CODES
     public static final String SUCCESSFUL_SIGNUP_USER_CODE = "200-1";
     public static final String SUCCESSFUL_LOGIN_USER_CODE = "200-2";
     public static final String SUCCESSFUL_LOGIN_USER_BY_TOKEN_CODE = "200-3";
     public static final String SUCCESSFUL_DELETE_USER_CODE = "200-4";

     //APP-TYPES
    public static final String LOGIN_AUTHORITY = "LOGIN_AUTHORITY";

    public static final Map<String, Error> errorMessages = new HashMap<>();
    public static final Map<String, Message> successfulMessages = new HashMap<>();



    static {
        errorMessages.put(ERROR_MISSING_ARGUMENTS_CODE,
                new Error(HttpStatus.BAD_REQUEST.value(), ERROR_MISSING_ARGUMENTS_CODE, FAILED_OPERATION_TITLE, "Missing arguments" ));
        errorMessages.put(ERROR_SIGNUP_USER_CODE,
                new Error(HttpStatus.BAD_REQUEST.value(), ERROR_SIGNUP_USER_CODE, FAILED_OPERATION_TITLE, "Fail sign-up user" ));
        errorMessages.put(ERROR_LOGIN_USER_CODE,
                    new Error(HttpStatus.BAD_REQUEST.value(), ERROR_LOGIN_USER_CODE, FAILED_OPERATION_TITLE, "Fail login user" ));
        errorMessages.put(ERROR_LOGIN_BY_TOKEN_CODE,
                new Error(HttpStatus.BAD_REQUEST.value(), ERROR_LOGIN_BY_TOKEN_CODE, FAILED_OPERATION_TITLE, "Invalid token" ));
       errorMessages.put(ERROR_REMOVE_USER,
                    new Error(HttpStatus.BAD_REQUEST.value(), ERROR_REMOVE_USER, FAILED_OPERATION_TITLE, "Invalid user" ));



        successfulMessages.put(SUCCESSFUL_SIGNUP_USER_CODE,
               new Message(PATH_USER_SIGNUP, DESCRIPTION_SUCCESSFUL_SIGN_UP, TITLE_SUCCESSFUL_OPERATION, 200  ));
        successfulMessages.put(SUCCESSFUL_LOGIN_USER_CODE,
                new Message(PATH_USER_LOGIN, DESCRIPTION_SUCCESSFUL_LOGIN, TITLE_SUCCESSFUL_OPERATION, 200  ));
    }

    public static Error getErrorMessage(String code){

          return errorMessages.get(code);
    }

    public static Message getSuccessMessage(String code){

        return successfulMessages.get(code);
    }
}
