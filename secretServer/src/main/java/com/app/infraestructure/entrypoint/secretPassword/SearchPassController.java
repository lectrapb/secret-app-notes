package com.app.infraestructure.entrypoint.secretPassword;

import com.app.domain.model.response.ApiResponse;
import com.app.domain.model.secretPassword.secretFindRequestDTO;
import com.app.domain.model.secretPassword.secretFindResponseDTO;
import com.app.domain.model.util.Constant;
import com.app.domain.usecases.secrets.secretPassUseCase.SecretSearchUseCase;
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
public class SearchPassController {

    @Autowired
    private SecretSearchUseCase searchUseCase;

    @PostMapping(Constant.PATH_SECRET_PASS_SELECT)
    public Mono<ResponseEntity<ApiResponse>> secretSelect(@RequestBody secretFindRequestDTO requestDTO){
        System.out.println("/api/secret-server/secret/update -->" + requestDTO);
        return Mono.fromCallable(() -> requestDTO)
                .flatMap(searchUseCase::findPassword)
                .map(p -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(new ApiResponse()
                                .createOnSuccess()
                                .setMessage(Constant.SUCCESSFUL_SELECT_PASSWORD_CODE)
                                .setData2(Constant.SECRET_SELECT, p.toArray())))
                .onErrorResume(e -> Mono.just(e)
                        .flatMap(t ->{
                            ApiResponse apiResponse = new ApiResponse().createOnError(t.getMessage());
                            return Mono.just(ResponseEntity
                                    .badRequest()
                                    .body(apiResponse));
                        }));
    }
}
