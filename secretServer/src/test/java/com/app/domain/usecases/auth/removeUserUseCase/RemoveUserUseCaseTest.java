package com.app.domain.usecases.auth.removeUserUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.token.gateway.TokenService;
import com.app.domain.model.user.gateway.UserRemoveRepository;
import com.app.domain.model.user.gateway.UserSearchRepository;
import com.app.domain.model.util.Constant;
import com.app.domain.usecases.auth.domain.ConstanTest;
import com.app.domain.usecases.auth.domain.TokenMother;
import com.app.domain.usecases.auth.domain.UserMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RemoveUserUseCaseTest {


    private UserSearchRepository searchRepository;
    private UserRemoveRepository removeRepository;

    private TokenService tokenService;

    private RemoveUserUseCase useCase;

    @BeforeEach
    void setUp(){
        searchRepository = mock(UserSearchRepository.class);
        removeRepository = mock(UserRemoveRepository.class);
        tokenService = mock (TokenService.class);
        useCase = new RemoveUserUseCase(searchRepository,removeRepository, tokenService);
    }

    @Test
    void remove_user_use_case_null_test(){

        var response =  useCase.removeUser(null, null);
        StepVerifier.create(response)
                .expectErrorMatches(err -> err instanceof BusinessException &&
                        err.getMessage().equals(Constant.ERROR_MISSING_ARGUMENTS_CODE))
                .verify();
    }

    @Test
    void remove_user_use_case_bad_uid_test(){

        when(removeRepository.deleteById(any())).thenReturn(Mono.empty());
        when(searchRepository.findById(any())).thenReturn(Mono.empty());

        var response = useCase.removeUser(ConstanTest.UID, "");
        StepVerifier.create(response)
                .expectErrorMatches(err -> err instanceof  BusinessException
                        && err.getMessage().equals(Constant.ERROR_REMOVE_USER))
                .verify();
    }

    @Test
    void remove_user_use_case_bad_token_uid_test(){

        when(searchRepository.findById(any())).thenReturn(UserMother.dataOk());
        when(tokenService.validateToken(any())).thenReturn(TokenMother.uidBad());

        var response = useCase.removeUser(ConstanTest.UID, "");
        StepVerifier.create(response)
                .expectErrorMatches(err -> err instanceof BusinessException
                                     && err.getMessage().equals(Constant.ERROR_REMOVE_USER))
                .verify();
    }

    @Test
    void remove_user_use_case_ok_test(){

        when(searchRepository.findById(any())).thenReturn(UserMother.dataOk());
        when(removeRepository.deleteById(any())).thenReturn(Mono.empty());
        when(tokenService.validateToken(any())).thenReturn(TokenMother.tokenOk());

        var response = useCase.removeUser(ConstanTest.UID, "");
        StepVerifier.create(response)
                .consumeNextWith(Assertions::assertNotNull)
                .verifyComplete();
    }

}