package com.app.infraestructure.entrypoint;

import com.app.domain.model.response.ApiResponse;
import com.app.domain.model.secretPassword.secretDeleteRequestDTO;
import com.app.domain.model.secretPassword.secretPasswordRequestDTO;
import com.app.domain.model.secretPassword.secretUpdateRequestDTO;
import com.app.domain.model.util.Constant;
import com.app.domain.usecases.auth.secretPassUseCase.SecretPassUseCase;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RegisterPasswordController {

    @Autowired
    private  SecretPassUseCase passUseCase;

    @PostMapping(Constant.PATH_SECRET_PASS_REGISTER)
    public Mono<ResponseEntity<ApiResponse>> secretRegister(@RequestBody secretPasswordRequestDTO requestDTO){
        System.out.println("/api/secret-server/secret/register -->" + requestDTO);
        return Mono.fromCallable(() -> requestDTO)
                .flatMap(passUseCase::registerPassword)
                .map(p -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(new ApiResponse().createOnSuccess().setMessage(p.getCode())))
                .onErrorResume(e -> Mono.just(e)
                        .flatMap(t ->{
                            ApiResponse apiResponse = new ApiResponse().createOnError(t.getMessage());
                            return Mono.just(ResponseEntity
                                    .badRequest()
                                    .body(apiResponse));
                        }));
    }

    @PostMapping(Constant.PATH_SECRET_PASS_DELETE)
    public Mono<ResponseEntity<ApiResponse>> secretDelete(@RequestBody secretDeleteRequestDTO requestDTO){
        System.out.println("/api/secret-server/secret/delete -->" + requestDTO);
        return Mono.fromCallable(() -> requestDTO)
                .flatMap(passUseCase::deletePassword)
                .map(p -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(new ApiResponse().createOnSuccess().setMessage(p.getCode())))
                .onErrorResume(e -> Mono.just(e)
                        .flatMap(t ->{
                            ApiResponse apiResponse = new ApiResponse().createOnError(t.getMessage());
                            return Mono.just(ResponseEntity
                                    .badRequest()
                                    .body(apiResponse));
                        }));
    }

    @PostMapping(Constant.PATH_SECRET_PASS_UPDATE)
    public Mono<ResponseEntity<ApiResponse>> secretUpdate(@RequestBody secretUpdateRequestDTO requestDTO){
        System.out.println("/api/secret-server/secret/update -->" + requestDTO);
        return Mono.fromCallable(() -> requestDTO)
                .flatMap(passUseCase::updatePassword)
                .map(p -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(new ApiResponse().createOnSuccess().setMessage(p.getCode())))
                .onErrorResume(e -> Mono.just(e)
                        .flatMap(t ->{
                            ApiResponse apiResponse = new ApiResponse().createOnError(t.getMessage());
                            return Mono.just(ResponseEntity
                                    .badRequest()
                                    .body(apiResponse));
                        }));
    }
}
