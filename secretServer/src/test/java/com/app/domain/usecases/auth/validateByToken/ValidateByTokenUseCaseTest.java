package com.app.domain.usecases.auth.validateByToken;

import com.app.config.BusinessException;
import com.app.domain.model.token.gateway.TokenService;
import com.app.domain.model.user.gateway.UserSearchRepository;
import com.app.domain.model.util.Constant;
import com.app.domain.usecases.auth.domain.TokenMother;
import com.app.domain.usecases.auth.domain.UserMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ValidateByTokenUseCaseTest {

    private UserSearchRepository search;
    private TokenService tokenService;

    private ValidateByTokenUseCase useCase;

    @BeforeEach
    void setUp() {
        search = mock(UserSearchRepository.class);
        tokenService = mock(TokenService.class);
        useCase = new  ValidateByTokenUseCase(search, tokenService);
    }

    @Test
    void validate_by_token_null_test(){

        var response = useCase.validate(null);
        StepVerifier.create(response)
                .expectErrorMatches(err -> err instanceof BusinessException &&
                        err.getMessage().equals(Constant.ERROR_MISSING_ARGUMENTS_CODE))
                .verify();
    }

    @Test
    void validate_by_token_ok_test(){

        when(search.findById(any(String.class))).thenReturn(UserMother.dataOk());
        when(tokenService.validateToken(any(String.class))).thenReturn(TokenMother.tokenOk());

        var response = useCase.validate("");
        StepVerifier.create(response)
                .consumeNextWith(Assertions::assertNotNull)
                .verifyComplete();
    }
}