package com.app.infraestructure.entrypoint.auth;


import com.app.domain.model.response.ApiResponse;
import com.app.domain.model.user.UserRemoveRequestDTO;
import com.app.domain.model.util.Constant;
import com.app.domain.usecases.auth.removeUserUseCase.RemoveUserUseCase;
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
public class RemoveUserController {

    private final RemoveUserUseCase useCase;

    @PostMapping(Constant.PATH_REMOVE_USER)
    public Mono<ResponseEntity<ApiResponse>> removeUser(@RequestBody UserRemoveRequestDTO request){

         var response  = Mono.fromCallable(() -> request)
                 .flatMap(requestDTO -> useCase.removeUser(requestDTO.getUid(), requestDTO.getToken()))
                 .map(p -> ResponseEntity
                         .status(HttpStatus.OK)
                         .contentType(MediaType.APPLICATION_JSON)
                         .body(new ApiResponse().createOnSuccess().setMessage(p.getCode())))
                 .onErrorResume(thr -> Mono.just(thr)
                         .flatMap(e ->{
                               ApiResponse apiResponse = new ApiResponse().createOnError(e.getMessage());
                               return  Mono.just(ResponseEntity
                                                 .badRequest()
                                                 .body(apiResponse));
                         }));
         return response;
    }
}
