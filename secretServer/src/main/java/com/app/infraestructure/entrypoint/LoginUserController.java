package com.app.infraestructure.entrypoint;


import com.app.domain.model.response.ApiResponse;
import com.app.domain.model.user.UserLoginRequestDTO;
import com.app.domain.model.util.Constant;
import com.app.domain.usecases.auth.loginUserUseCase.LoginUseCase;
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
public class LoginUserController {

        private final LoginUseCase useCase;

        @PostMapping(Constant.PATH_USER_LOGIN)
        public Mono<ResponseEntity<ApiResponse>> login(@RequestBody UserLoginRequestDTO request){

             return Mono.fromCallable(() -> request)
                     .flatMap(useCase::login)
                     .map(p -> ResponseEntity
                             .status(HttpStatus.OK)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body(new ApiResponse()
                                       .createOnSuccess()
                                       .setMessage(Constant.SUCCESSFUL_LOGIN_USER_CODE)
                                       .setData(Constant.LOGIN_AUTHORITY, p, p.getName())))
                     .onErrorResume(thr -> Mono.just(thr)
                             .flatMap(e ->{
                                 ApiResponse apiResponse = new ApiResponse().createOnError(e.getMessage());
                                 return Mono.just(ResponseEntity
                                         .badRequest()
                                         .body(apiResponse));
                             }));
        }
}
