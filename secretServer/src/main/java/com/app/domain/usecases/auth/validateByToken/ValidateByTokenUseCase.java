package com.app.domain.usecases.auth.validateByToken;

import com.app.config.BusinessException;
import com.app.domain.model.token.Token;
import com.app.domain.model.token.gateway.TokenService;
import com.app.domain.model.user.UserLoginResponseDTO;
import com.app.domain.model.user.gateway.UserSearchRepository;
import com.app.domain.model.util.Constant;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class ValidateByTokenUseCase {


    private final UserSearchRepository searchRepository;
    private final TokenService tokenService;

    public Mono<UserLoginResponseDTO> validate(String token){

       return   Mono.fromCallable(() -> token)
                .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE)))
                .flatMap(completeToken -> {
                     Token validateToken = tokenService.validateToken(token);
                     if(validateToken.isValid()){
                         UserLoginResponseDTO responseDTO = new UserLoginResponseDTO();
                         return searchRepository.findById(validateToken.getUid())
                                 .map(user -> {
                                     responseDTO.setUid(user.getUid());
                                     responseDTO.setEmail(user.getEmail());
                                     responseDTO.setName(user.getName());
                                     responseDTO.setToken(tokenService.createToken(user.getUid()));
                                     return responseDTO;
                                  });
                     }
                     return Mono.error(new BusinessException(Constant.ERROR_LOGIN_BY_TOKEN_CODE));
                });


    }
}
