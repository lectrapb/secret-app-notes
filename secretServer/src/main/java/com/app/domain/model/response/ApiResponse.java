package com.app.domain.model.response;

import com.app.domain.model.secretNote.secretFindNoteRequestDTO;
import com.app.domain.model.secretNote.secretFindNoteResponseDTO;
import com.app.domain.model.secretPassword.secretFindResponseDTO;
import com.app.domain.model.util.Constant;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    public ApiResponse setData2(String type, Object[] result){
        List<secretFindResponseDTO> dataPass = new ArrayList<>();
        for (Object obj : result){
            dataPass.add((secretFindResponseDTO) obj);
        }

        Data<?>[] arrayData;
        if(result != null){
            arrayData = new Data[result.length];
            for (int i = 0; i < result.length; i++){
                arrayData[i] = new Data<>(type, dataPass.get(i).getId(), result[i]);
            }
        }else{
            arrayData = new Data[0];
        }

        this.data = arrayData;
        return this;
    }

    public ApiResponse setData3(String type, Object[] result){
        List<secretFindNoteResponseDTO> dataNote = new ArrayList<>();
        for (Object obj : result){
            dataNote.add((secretFindNoteResponseDTO) obj);
        }

        Data<?>[] arrayData;
        if(result != null){
            arrayData = new Data[result.length];
            for (int i = 0; i < result.length; i++){
                arrayData[i] = new Data<>(type, dataNote.get(i).getId(), result[i]);
            }
        }else{
            arrayData = new Data[0];
        }

        this.data = arrayData;
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
