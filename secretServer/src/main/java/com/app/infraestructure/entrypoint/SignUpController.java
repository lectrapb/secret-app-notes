package com.app.infraestructure.entrypoint;

import com.app.domain.model.response.ApiResponse;
import com.app.domain.model.user.UserSignUpResquestDTO;
import com.app.domain.model.util.Constant;
import com.app.domain.usecases.auth.signUpUseCase.SignUpUseCase;
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
public class SignUpController {

    private final SignUpUseCase useCase;

    @PostMapping(Constant.PATH_USER_SIGNUP)
    public Mono<ResponseEntity<ApiResponse>> signUp(@RequestBody UserSignUpResquestDTO request){

             return Mono.fromCallable(() -> request)
                             .flatMap(useCase::registerUser)
                             .map(p -> ResponseEntity
                                     .status(HttpStatus.CREATED)
                                     .contentType(MediaType.APPLICATION_JSON)
                                     .body(new ApiResponse().createOnSuccess().setMessage(p.getCode())))
                             .onErrorResume(thr -> Mono.just(thr)
                                              .flatMap(e ->{
                                                  ApiResponse apiResponse = new ApiResponse().createOnError(e.getMessage());
                                                  return Mono.just(ResponseEntity
                                                                    .badRequest()
                                                                    .body(apiResponse));
                                              }));

    }
}
