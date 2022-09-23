package com.app.domain.usecases.auth.loginUserUseCase;


import com.app.domain.model.user.UserLoginRequestDTO;
import com.app.domain.model.user.UserLoginResponseDTO;
import com.app.domain.model.user.gateway.UserSearchRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class LoginUseCase {


    private final UserSearchRepository searchRepository;

    public Mono<UserLoginResponseDTO> login(UserLoginRequestDTO requestDTO){

         return null;
    }

}
