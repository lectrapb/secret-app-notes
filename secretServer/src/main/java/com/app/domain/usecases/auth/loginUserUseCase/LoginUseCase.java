package com.app.domain.usecases.auth.loginUserUseCase;


import com.app.config.BusinessException;
import com.app.domain.model.password.PasswordEncryptService;
import com.app.domain.model.token.gateway.TokenService;
import com.app.domain.model.user.UserLogin;
import com.app.domain.model.user.UserLoginRequestDTO;
import com.app.domain.model.user.UserLoginResponseDTO;
import com.app.domain.model.user.gateway.UserSearchRepository;
import com.app.domain.model.user.valueobject.EmailUser;
import com.app.domain.model.user.valueobject.PasswordUser;
import com.app.domain.model.util.Constant;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicReference;

@AllArgsConstructor
public class LoginUseCase {


    private final UserSearchRepository searchRepository;
    private final TokenService tokenService;

    private final PasswordEncryptService encryptService;

    public Mono<UserLoginResponseDTO> login(UserLoginRequestDTO requestDTO){

                AtomicReference<String> password = new AtomicReference<>("");
                return  Mono.fromCallable(() ->
                        new UserLogin(new EmailUser(requestDTO.getEmail()),
                                       new PasswordUser(requestDTO.getPassword())))
                        .onErrorResume(err -> Mono.error(new BusinessException(err.getMessage())))
                        .flatMap(userLogin -> {
                            password.set(userLogin.getPassword().value());
                            return searchRepository.findByEmail(userLogin.getEmail().value());
                        })
                        .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_LOGIN_USER_CODE)))
                        .map(currentUser ->{
                              String evaluatePassword = password.get();
                              boolean match = encryptService.checkPassword(evaluatePassword, currentUser.getPassword());
                              if(!match){
                                  throw new BusinessException(Constant.ERROR_LOGIN_USER_CODE);
                              }
                            return  new UserLoginResponseDTO( currentUser.getUid(),
                                                              currentUser.getName(),
                                                              currentUser.getEmail(),
                                                              tokenService.createToken(currentUser.getUid()));
                        });

    }

}
