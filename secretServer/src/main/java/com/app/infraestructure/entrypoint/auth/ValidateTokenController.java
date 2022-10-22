package com.app.infraestructure.entrypoint.auth;


import com.app.domain.model.response.ApiResponse;
import com.app.domain.model.util.Constant;
import com.app.domain.usecases.auth.validateByToken.ValidateByTokenUseCase;
import lombok.AllArgsConstructor;
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
public class ValidateTokenController {

    private final ValidateByTokenUseCase useCase;

    @PostMapping(Constant.PATH_VALIDATE_TOKEN)
    public Mono<ResponseEntity<ApiResponse>> validateToken(@RequestBody TokenRequestDTO token) {


       return  Mono.fromCallable(token::value)
                .flatMap(useCase::validate)
                .map(dto -> ResponseEntity
                        .status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(new ApiResponse()
                                .createOnSuccess()
                                .setMessage(Constant.SUCCESSFUL_LOGIN_USER_BY_TOKEN_CODE)
                                .setData(Constant.LOGIN_AUTHORITY,dto, dto.getUid() ))
                ).onErrorResume(thr -> Mono.just(thr)
                .flatMap(e ->{
                     ApiResponse apiResponse = new ApiResponse().createOnError(e.getMessage());
                     return Mono.just(ResponseEntity
                             .badRequest()
                             .body(apiResponse));
                }));



    }

}
