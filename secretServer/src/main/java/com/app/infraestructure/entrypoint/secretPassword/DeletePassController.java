package com.app.infraestructure.entrypoint.secretPassword;

import com.app.domain.model.response.ApiResponse;
import com.app.domain.model.secretPassword.secretDeleteRequestDTO;
import com.app.domain.model.util.Constant;
import com.app.domain.usecases.secrets.secretPassUseCase.SecretDeleteUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class DeletePassController {

    @Autowired
    private SecretDeleteUseCase deleteUseCase;

    @PostMapping(Constant.PATH_SECRET_PASS_DELETE)
    public Mono<ResponseEntity<ApiResponse>> secretDelete(@RequestBody secretDeleteRequestDTO requestDTO){
        return Mono.fromCallable(() -> requestDTO)
                .flatMap(deleteUseCase::deletePassword)
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
