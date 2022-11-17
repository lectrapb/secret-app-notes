
package com.app.domain.model.util;

import  com.app.domain.model.response.Error;
import com.app.domain.model.response.Message;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class Constant {

    //APLICATION-PATHS
    public static final String PATH_USER_LOGIN  = "/api/secret-server/signIn";
    public static final String PATH_USER_SIGNUP = "/api/secret-server/signUp";
    public static final String PATH_SECRET_PASS_REGISTER = "/api/secret-server/secret/register";
    public static final String PATH_SECRET_PASS_DELETE = "/api/secret-server/secret/delete";
    public static final String PATH_SECRET_PASS_UPDATE = "/api/secret-server/secret/update";
    public static final String PATH_SECRET_PASS_SELECT = "/api/secret-server/secret/select";
    public static final String PATH_SECRET_NOTE_REGISTER = "/api/secret-server/secret/note/register";
    public static final String PATH_SECRET_NOTE_SELECT = "/api/secret-server/secret/note/select";
    public static final String PATH_SECRET_NOTE_UPDATE = "/api/secret-server/secret/note/update";
    public static final String PATH_SECRET_NOTE_DELETE = "/api/secret-server/secret/note/delete";
    public static final String PATH_DELETE_ALL_SECRET = "/api/secret-server/secret/all/delete";
    public static final String PATH_VERIFY_PASS = "/api/secret-server/verify/password";


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
    public static final String DESCRIPTION_SUCCESSFUL_SECRET_NOTE =" Successful register secret note  ";
    public static final String DESCRIPTION_SUCCESSFUL_UPDATE_NOTE =" Successful update secret note  ";
    public static final String DESCRIPTION_SUCCESSFUL_DELETE_ZERO_NOTE =" 0 Rows Affected  ";
    public static final String DESCRIPTION_SUCCESSFUL_DELETE_NOTE =" Successful delete secret note  ";
    public static final String DESCRIPTION_SUCCESSFUL_UPDATE_ZERO_NOTE =" 0 Rows Affected  ";
    public static final String DESCRIPTION_SUCCESSFUL_VERIFY_PASS_NOTE =" Password is not compromised  ";
    public static final String DESCRIPTION_SUCCESSFUL_INSECURE_PASS_NOTE =" Password is compromised  ";
    public static final String DESCRIPTION_SUCCESSFUL_ZERO_DELETE_ALL = " 0 Rows Affected ";
    public static final String DESCRIPTION_SUCCESSFUL_DELETE_ALL = " Successful delete all secret ";
     //ERROR CODES
     public static final String ERROR_MISSING_ARGUMENTS_CODE = "ER-401";
     public static final String ERROR_SIGNUP_USER_CODE = "ER-402";
     public static final String ERROR_LOGIN_USER_CODE  = "ER-403";
     public static final String ERROR_LOGIN_BY_TOKEN_CODE  = "ER-404";
     public static final String ERROR_SECRET_PASS_CODE = "ER-450";
     public static final String ERROR_SECRET_NOTE_CODE = "ER-451";
     public static final String ERROR_EXCEED_LIMIT_CODE = "ER-452";
     public static final String ERROR_INSECURE_PASSWORD_CODE = "ER-453";
     //SUCCESS-CODES
     public static final String SUCCESSFUL_SIGNUP_USER_CODE = "800-1";
     public static final String SUCCESSFUL_SECRET_PASSWORD_CODE = "850-1";
     public static final String SUCCESSFUL_DELETE_PASSWORD_CODE = "851-1";
     public static final String SUCCESSFUL_SELECT_PASSWORD_CODE = "852-1";
     public static final String SUCCESSFUL_UPDATE_PASSWORD_CODE = "853-1";
     public static final String SUCCESSFUL_DELETE_ZERO_PASSWORD_CODE = "854-1";
     public static final String SUCCESSFUL_UPDATE_ZERO_PASSWORD_CODE = "855-1";
     public static final String SUCCESSFUL_SECRET_NOTE_CODE = "856-1";
     public static final String SUCCESSFUL_UPDATE_ZERO_NOTE_CODE = "857-1";
     public static final String SUCCESSFUL_UPDATE_NOTE_CODE = "858-1";
     public static final String SUCCESSFUL_DELETE_ZERO_NOTE_CODE = "859-1";
     public static final String SUCCESSFUL_DELETE_NOTE_CODE = "860-1";
     public static final String SUCCESSFUL_VERIFY_PASSWORD_CODE = "861-1";
     public static final String SUCCESSFUL_INSECURE_PASSWORD_CODE = "862-1";
     public static final String SUCCESSFUL_DELETE_ZERO_ALL_CODE = "863-1";
     public static final String SUCCESSFUL_DELETE_ALL_CODE = "864-1";
     public static final String SUCCESSFUL_LOGIN_USER_CODE = "800-2";

     //APP-TYPES
    public static final String LOGIN_AUTHORITY = "LOGIN_AUTHORITY";
    public static final String SECRET_SELECT = "SECRET_SELECT";
    public static final String NOTE_SELECT = "NOTE_SELECT";

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
        errorMessages.put(ERROR_EXCEED_LIMIT_CODE,
                new Error(HttpStatus.BAD_REQUEST.value(), ERROR_EXCEED_LIMIT_CODE, FAILED_OPERATION_TITLE, "Exceed Character Limit " ));
        errorMessages.put(ERROR_INSECURE_PASSWORD_CODE,
                new Error(HttpStatus.BAD_REQUEST.value(), ERROR_INSECURE_PASSWORD_CODE, FAILED_OPERATION_TITLE, "Password is compromised " ));


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
        successfulMessages.put(SUCCESSFUL_SECRET_NOTE_CODE,
                new Message(PATH_SECRET_NOTE_REGISTER, DESCRIPTION_SUCCESSFUL_SECRET_NOTE, TITLE_SUCCESSFUL_OPERATION, 200  ));
        successfulMessages.put(SUCCESSFUL_UPDATE_NOTE_CODE,
                new Message(PATH_SECRET_NOTE_UPDATE, DESCRIPTION_SUCCESSFUL_UPDATE_NOTE, TITLE_SUCCESSFUL_OPERATION, 200  ));
        successfulMessages.put(SUCCESSFUL_UPDATE_ZERO_NOTE_CODE,
                new Message(PATH_SECRET_NOTE_UPDATE, DESCRIPTION_SUCCESSFUL_UPDATE_ZERO_NOTE, TITLE_SUCCESSFUL_OPERATION, 200  ));
        successfulMessages.put(SUCCESSFUL_DELETE_ZERO_NOTE_CODE,
                new Message(PATH_SECRET_NOTE_DELETE, DESCRIPTION_SUCCESSFUL_DELETE_ZERO_NOTE, TITLE_SUCCESSFUL_OPERATION, 200  ));
        successfulMessages.put(SUCCESSFUL_DELETE_NOTE_CODE,
                new Message(PATH_SECRET_NOTE_DELETE, DESCRIPTION_SUCCESSFUL_DELETE_NOTE, TITLE_SUCCESSFUL_OPERATION, 200  ));
        successfulMessages.put(SUCCESSFUL_VERIFY_PASSWORD_CODE,
                new Message(PATH_VERIFY_PASS, DESCRIPTION_SUCCESSFUL_VERIFY_PASS_NOTE, TITLE_SUCCESSFUL_OPERATION, 200  ));
        successfulMessages.put(SUCCESSFUL_INSECURE_PASSWORD_CODE,
                new Message(PATH_VERIFY_PASS, DESCRIPTION_SUCCESSFUL_INSECURE_PASS_NOTE, TITLE_SUCCESSFUL_OPERATION, 200  ));
        successfulMessages.put(SUCCESSFUL_DELETE_ZERO_ALL_CODE,
                new Message(PATH_DELETE_ALL_SECRET, DESCRIPTION_SUCCESSFUL_ZERO_DELETE_ALL, TITLE_SUCCESSFUL_OPERATION, 200  ));
        successfulMessages.put(SUCCESSFUL_DELETE_ALL_CODE,
                new Message(PATH_DELETE_ALL_SECRET, DESCRIPTION_SUCCESSFUL_DELETE_ALL, TITLE_SUCCESSFUL_OPERATION, 200  ));
    }

    public static Error getErrorMessage(String code){

          return errorMessages.get(code);
    }

    public static Message getSuccessMessage(String code){

        return successfulMessages.get(code);
    }
}
