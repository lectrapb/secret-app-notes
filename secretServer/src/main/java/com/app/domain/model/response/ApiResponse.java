package com.app.domain.model.response;

import com.app.domain.model.secretNote.secretFindNoteRequestDTO;
import com.app.domain.model.util.Constant;
import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor
public class ApiResponse {

    private Data<?>[] data;

    private Message message;

    private Error[] errors;

    public  ApiResponse createOnError(String errorCode){

          return new ApiResponse()
                  .setErrors(errorCode);
    }

    public ApiResponse createOnSuccess(){
        return new ApiResponse();
    }

    public ApiResponse setData(String type, Object result, String id){

        this.data = (result != null) ?
                new Data[]{new Data<>(type, id, result)} :
                new Data[0];
        return this;
    }

    public ApiResponse setMessage(String code  ){

        this.message = Constant.getSuccessMessage(code);
        return this;
    }
    private ApiResponse setErrors(String codeError){
        System.out.println("apiresponse " + codeError);
        Error error = Constant.getErrorMessage(codeError);
        this.errors = new Error[]{error};
        return this;
    }

}
