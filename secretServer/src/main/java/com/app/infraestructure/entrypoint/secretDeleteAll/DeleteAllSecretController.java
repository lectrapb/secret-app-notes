package com.app.infraestructure.entrypoint.secretDeleteAll;

import com.app.domain.model.response.ApiResponse;
import com.app.domain.model.secretDeleteAll.secretDeleteAllRequestDTO;
import com.app.domain.model.util.Constant;
import com.app.domain.usecases.secrets.secretDeleteAllUseCase.SecretDeleteAllUseCase;
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
public class DeleteAllSecretController {

    @Autowired
    private SecretDeleteAllUseCase secretDeleteAllUseCase;

    @PostMapping(Constant.PATH_DELETE_ALL_SECRET)
    public Mono<ResponseEntity<ApiResponse>> secretDeleteAll(@RequestBody secretDeleteAllRequestDTO allRequestDTO){
        return Mono.fromCallable(() -> allRequestDTO)
                .flatMap(secretDeleteAllUseCase::deleteAllSecret)
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
