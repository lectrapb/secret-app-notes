package com.app.domain.usecases.auth.registerUserUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.user.User;
import com.app.domain.model.user.UserRegisterResponseDTO;
import com.app.domain.model.user.UserRegisterResquestDTO;
import com.app.domain.model.user.gateway.UserRepository;
import com.app.domain.model.util.Constant;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class RegisterUserUseCase {

    private final UserRepository userRepository;

    public Mono<UserRegisterResponseDTO> registerUser(UserRegisterResquestDTO requestDTO){

          return Mono.fromCallable(() -> requestDTO)
                  .switchIfEmpty(Mono.error(new BusinessException(Constant.MISSING_ARGUMENTS_DESCRIPTION)))
                  .map( dto -> {
                       User user =  MapperRegister.toUser(requestDTO);
                       userRepository.saveUser(user);
                       return user;
                  }).flatMap(this::prepareOkResponse);
    }

    private Mono<UserRegisterResponseDTO> prepareOkResponse(User data) {

          return Mono.fromCallable(()-> data)
                  .map(user ->{
                       UserRegisterResponseDTO responseDTO = new UserRegisterResponseDTO();
                       return responseDTO;
                  });
    }
}
