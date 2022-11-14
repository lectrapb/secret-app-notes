package com.app.domain.usecases.auth.loginUserUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.password.PasswordEncryptService;
import com.app.domain.model.token.gateway.TokenService;
import com.app.domain.model.user.gateway.UserSearchRepository;
import com.app.domain.model.util.Constant;
import com.app.domain.usecases.auth.domain.LoginReqMother;
import com.app.domain.usecases.auth.domain.UserMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginUseCaseTest {

    private UserSearchRepository searchRepository;

    private LoginUseCase useCase;

    private TokenService tokenService;

    private PasswordEncryptService encryptService;

    @BeforeEach
    void setUp() {
         searchRepository = mock(UserSearchRepository.class);
         tokenService = mock(TokenService.class);
         encryptService = mock (PasswordEncryptService.class);
         useCase = new LoginUseCase(searchRepository, tokenService, encryptService);
    }

    @Test
    void user_login_null_test(){

        var response  = useCase.login(LoginReqMother.dataNull());
        StepVerifier.create(response)
                    .expectErrorMatches(err -> err instanceof BusinessException &&
                               err.getMessage().equals(Constant.ERROR_MISSING_ARGUMENTS_CODE))
                    .verify();
    }

    @Test
    void user_login_ok_test(){

        when(searchRepository.findByEmail(any())).thenReturn(UserMother.dataOk());
        when(tokenService.createToken(any())).thenReturn("Token-test");
        when(encryptService.checkPassword(any(), any())).thenReturn(true);

        var response = useCase.login(LoginReqMother.dataOk());
        StepVerifier.create(response)
                .consumeNextWith(Assertions::assertNotNull)
                .verifyComplete();
    }







}