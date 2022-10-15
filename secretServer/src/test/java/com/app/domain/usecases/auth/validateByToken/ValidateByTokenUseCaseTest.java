package com.app.domain.usecases.auth.validateByToken;

import com.app.config.BusinessException;
import com.app.domain.model.token.gateway.TokenService;
import com.app.domain.model.user.gateway.UserSearchByIdRepository;
import com.app.domain.model.util.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.mock;

class ValidateByTokenUseCaseTest {

    private UserSearchByIdRepository search;
    private TokenService tokenService;

    private ValidateByTokenUseCase useCase;

    @BeforeEach
    void setUp() {
        search = mock(UserSearchByIdRepository.class);
        tokenService = mock(TokenService.class);
        useCase = new  ValidateByTokenUseCase(search, tokenService);
    }

    @Test
    void validate_bytoken_null_test(){

        var response = useCase.validate(null);
        StepVerifier.create(response)
                .expectErrorMatches(err -> err instanceof BusinessException &&
                        err.getMessage().equals(Constant.ERROR_MISSING_ARGUMENTS_CODE))
                .verify();
    }
}