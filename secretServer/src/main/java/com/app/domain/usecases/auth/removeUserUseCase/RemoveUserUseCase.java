package com.app.domain.usecases.auth.removeUserUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.token.Token;
import com.app.domain.model.token.gateway.TokenService;
import com.app.domain.model.user.User;
import com.app.domain.model.user.UserRemoveResponseDTO;
import com.app.domain.model.user.gateway.UserRemoveRepository;
import com.app.domain.model.user.gateway.UserSearchRepository;
import com.app.domain.model.util.Constant;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;


@AllArgsConstructor
public class RemoveUserUseCase {

    private final UserSearchRepository searchRepository;
    private final UserRemoveRepository removeRepository;
    private final TokenService tokenService;

    public Mono<UserRemoveResponseDTO> removeUser(String uid, String token ){

        var response =  Mono.fromCallable(() ->uid)
                .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE)))
                .flatMap(searchRepository::findById)
                .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_REMOVE_USER)))
                .map(user -> validateUserToken(user, token))
                .map(user -> removeRepository.deleteById(user.getUid()))
                .flatMap(voidMono -> createResponse());

        return response;
    }

    private User validateUserToken(User user, String token ){

        if(token != null) {
            Token tokenOk = tokenService.validateToken(token);
            if (tokenOk.isValid()) {
                boolean userValid = tokenOk.getUid().equals(user.getUid());
                if (userValid) {
                    return user;
                }
                throw new BusinessException(Constant.ERROR_REMOVE_USER);
            }
            throw new BusinessException(Constant.ERROR_LOGIN_BY_TOKEN_CODE);
        }
        throw new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE);
    }
    private Mono<UserRemoveResponseDTO> createResponse(){
          return Mono.just( UserRemoveResponseDTO.builder()
                  .code(Constant.SUCCESSFUL_DELETE_USER_CODE)
                  .build());
    }

}
