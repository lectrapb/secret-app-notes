package com.app.domain.usecases.auth.signUpUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.user.User;
import com.app.domain.model.user.UserSignUpResquestDTO;
import com.app.domain.model.user.gateway.UserSignUpRepository;
import com.app.domain.model.util.Constant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class SignUpUserUseCaseTest {

    private UserSignUpRepository userRepository;

    private SignUpUseCase useCase;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserSignUpRepository.class);
        useCase = new SignUpUseCase(userRepository);
    }

    @Test
    void user_signup_null_test() {

        var response = useCase.registerUser(null);
        StepVerifier.create(response)
                .expectErrorMatches(err -> err instanceof BusinessException &&
                        err.getMessage().equals(Constant.ERROR_MISSING_ARGUMENTS_CODE))
                .verify();

    }
    @Test
    void user_signup_email_null_test(){

        var response = useCase.registerUser(getRequestBadEmailDTO());
        StepVerifier.create(response)
                .expectErrorMatches(err -> err instanceof BusinessException )
                .verify();
    }

    @Test
    void user_signup_ok_test() {

        when(userRepository.save(isA(User.class))).thenReturn(Mono.empty());
        var response = useCase.registerUser(getResquestDTO());
        StepVerifier.create(response)
                .consumeNextWith(Assertions::assertNotNull)
                .verifyComplete();

    }

    private static UserSignUpResquestDTO getRequestBadEmailDTO(){

        UserSignUpResquestDTO resquestDTO = getResquestDTO();
        resquestDTO.setEmail(null);
        return resquestDTO;
    }
    private static UserSignUpResquestDTO getResquestDTO() {

        UserSignUpResquestDTO requestDTO = new UserSignUpResquestDTO();
        requestDTO.setEmail("");
        requestDTO.setRole("");
        requestDTO.setGoogle(false);
        requestDTO.setName("");
        requestDTO.setImage("");
        requestDTO.setPassword(null);

        return  requestDTO;
    }
}