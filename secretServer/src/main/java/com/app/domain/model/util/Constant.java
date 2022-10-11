
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
    public static final String PATH_SECRET_PASS_REGISTER = "/api/secret-server/secret/register";
    public static final String PATH_SECRET_PASS_DELETE = "/api/secret-server/secret/delete";
    public static final String PATH_SECRET_PASS_UPDATE = "/api/secret-server/secret/update";
    public static final String PATH_SECRET_PASS_SELECT = "/api/secret-server/secret/select";


     //APPLICATION-TITLES
     public static final String TITLE_SUCCESSFUL_OPERATION =  "SUCCESS OPERATION";
     public static final String FAILED_OPERATION_TITLE =  "FAILED OPERATION";

     //APPLICATION-DESCRIPTIONS
    public static final String DESCRIPTION_SUCCESSFUL_SIGN_UP =" Successful user signup  ";
    public static final String DESCRIPTION_SUCCESSFUL_LOGIN =" Successful user login  ";
    public static final String DESCRIPTION_SUCCESSFUL_SECRET_PASSWORD =" Successful register secret password  ";
    public static final String DESCRIPTION_SUCCESSFUL_DELETE_PASSWORD =" Successful delete secret password  ";
    public static final String DESCRIPTION_SUCCESSFUL_SELECT_PASSWORD =" Successful select secret password  ";
    public static final String DESCRIPTION_SUCCESSFUL_UPDATE_PASSWORD =" Successful update secret password  ";
    public static final String DESCRIPTION_SUCCESSFUL_DELETE_ZERO_PASSWORD =" 0 Rows Affected  ";
    public static final String DESCRIPTION_SUCCESSFUL_UPDATE_ZERO_PASSWORD =" 0 Rows Affected  ";
     //ERROR CODES
     public static final String ERROR_MISSING_ARGUMENTS_CODE = "ER-401";
     public static final String ERROR_SIGNUP_USER_CODE = "ER-402";
     public static final String ERROR_SECRET_PASS_CODE = "ER-450";
     public static final String ERROR_LOGIN_USER_CODE  = "ER-403";
     public static final String ERROR_LOGIN_BY_TOKEN_CODE  = "ER-404";
     //SUCCESS-CODES
     public static final String SUCCESSFUL_SIGNUP_USER_CODE = "800-1";
     public static final String SUCCESSFUL_SECRET_PASSWORD_CODE = "850-1";
     public static final String SUCCESSFUL_DELETE_PASSWORD_CODE = "851-1";
     public static final String SUCCESSFUL_SELECT_PASSWORD_CODE = "852-1";
     public static final String SUCCESSFUL_UPDATE_PASSWORD_CODE = "853-1";
     public static final String SUCCESSFUL_DELETE_ZERO_PASSWORD_CODE = "854-1";
     public static final String SUCCESSFUL_UPDATE_ZERO_PASSWORD_CODE = "855-1";
     public static final String SUCCESSFUL_LOGIN_USER_CODE = "800-2";

     //APP-TYPES
    public static final String LOGIN_AUTHORITY = "LOGIN_AUTHORITY";
    public static final String SECRET_SELECT = "SECRET_SELECT";

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



        successfulMessages.put(SUCCESSFUL_SIGNUP_USER_CODE,
               new Message(PATH_USER_SIGNUP, DESCRIPTION_SUCCESSFUL_SIGN_UP, TITLE_SUCCESSFUL_OPERATION, 200  ));
        successfulMessages.put(SUCCESSFUL_LOGIN_USER_CODE,
                new Message(PATH_USER_LOGIN, DESCRIPTION_SUCCESSFUL_LOGIN, TITLE_SUCCESSFUL_OPERATION, 200  ));
        successfulMessages.put(SUCCESSFUL_SECRET_PASSWORD_CODE,
                new Message(PATH_SECRET_PASS_REGISTER, DESCRIPTION_SUCCESSFUL_SECRET_PASSWORD, TITLE_SUCCESSFUL_OPERATION, 200  ));
        successfulMessages.put(SUCCESSFUL_DELETE_PASSWORD_CODE,
                new Message(PATH_SECRET_PASS_DELETE, DESCRIPTION_SUCCESSFUL_DELETE_PASSWORD, TITLE_SUCCESSFUL_OPERATION, 200  ));
        successfulMessages.put(SUCCESSFUL_SELECT_PASSWORD_CODE,
                new Message(PATH_SECRET_PASS_SELECT, DESCRIPTION_SUCCESSFUL_SELECT_PASSWORD, TITLE_SUCCESSFUL_OPERATION, 200  ));
        successfulMessages.put(SUCCESSFUL_UPDATE_PASSWORD_CODE,
                new Message(PATH_SECRET_PASS_UPDATE, DESCRIPTION_SUCCESSFUL_UPDATE_PASSWORD, TITLE_SUCCESSFUL_OPERATION, 200  ));
        successfulMessages.put(SUCCESSFUL_DELETE_ZERO_PASSWORD_CODE,
                new Message(PATH_SECRET_PASS_DELETE, DESCRIPTION_SUCCESSFUL_DELETE_ZERO_PASSWORD, TITLE_SUCCESSFUL_OPERATION, 200  ));
        successfulMessages.put(SUCCESSFUL_UPDATE_ZERO_PASSWORD_CODE,
                new Message(PATH_SECRET_PASS_UPDATE, DESCRIPTION_SUCCESSFUL_UPDATE_ZERO_PASSWORD, TITLE_SUCCESSFUL_OPERATION, 200  ));
    }

    public static Error getErrorMessage(String code){

          return errorMessages.get(code);
    }

    public static Message getSuccesMessage(String code){

        return successfulMessages.get(code);
    }
}
