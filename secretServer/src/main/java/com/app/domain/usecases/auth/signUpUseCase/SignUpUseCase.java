package com.app.domain.usecases.auth.signUpUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.user.UserSignUpResponseDTO;
import com.app.domain.model.user.UserSignUpResquestDTO;
import com.app.domain.model.user.gateway.UserSignUpRepository;
import com.app.domain.model.util.Constant;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class SignUpUseCase {

    private final UserSignUpRepository userRepository;

    public Mono<UserSignUpResponseDTO> registerUser(UserSignUpResquestDTO requestDTO){

          return Mono.fromCallable(() -> requestDTO)
                  .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE)))
                  .map(MapperSignUp::toUser)
                  .onErrorResume(e -> Mono.error(new BusinessException(e.getMessage())))
                  .map(userRepository::save)
                  .flatMap(dto ->prepareOkResponse());
    }

    private Mono<UserSignUpResponseDTO> prepareOkResponse() {

          return Mono.fromCallable(UserSignUpResponseDTO::new)
                  .map(dto -> {
                          dto.setCode(Constant.SUCCESSFUL_SIGNUP_USER_CODE);
                          return dto;
                  });
    }
}
