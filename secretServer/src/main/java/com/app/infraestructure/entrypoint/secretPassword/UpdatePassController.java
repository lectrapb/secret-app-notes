package com.app.infraestructure.entrypoint.secretPassword;

import com.app.domain.model.response.ApiResponse;
import com.app.domain.model.secretPassword.secretUpdateRequestDTO;
import com.app.domain.model.util.Constant;
import com.app.domain.usecases.secrets.secretPassUseCase.SecretUpdateUseCase;
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
public class UpdatePassController {

    @Autowired
    private SecretUpdateUseCase updateUseCase;

    @PostMapping(Constant.PATH_SECRET_PASS_UPDATE)
    public Mono<ResponseEntity<ApiResponse>> secretUpdate(@RequestBody secretUpdateRequestDTO requestDTO){
        System.out.println("/api/secret-server/secret/update -->" + requestDTO);
        return Mono.fromCallable(() -> requestDTO)
                .flatMap(updateUseCase::updatePassword)
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
