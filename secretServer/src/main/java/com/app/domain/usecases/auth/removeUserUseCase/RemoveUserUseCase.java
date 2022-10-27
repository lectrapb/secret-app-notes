package com.app.domain.usecases.auth.removeUserUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.user.gateway.UserRemoveRepository;
import com.app.domain.model.user.gateway.UserSearchRepository;
import com.app.domain.model.util.Constant;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;


@AllArgsConstructor
public class RemoveUserUseCase {

    private final UserSearchRepository searchRepository;
    private final UserRemoveRepository removeRepository;

    public Mono<UserRemoveDTO> removeUser(String uid){

        return Mono.fromCallable(() ->uid)
                .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE)))
                .flatMap(searchRepository::findById)
                .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_REMOVE_USER)))
                .map(user -> removeRepository.deleteById(user.getUid()))
                .flatMap(voidMono -> createResponse());
    }

    private Mono<UserRemoveDTO> createResponse(){
          return Mono.just( UserRemoveDTO.builder()
                  .code(Constant.SUCCESSFUL_DELETE_USER_CODE)
                  .build());
    }

}
