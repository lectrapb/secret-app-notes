package com.app.domain.usecases.auth.registerUserUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.user.User;
import com.app.domain.model.user.UserRegisterResquestDTO;
import com.app.domain.model.user.gateway.UserRepository;
import com.app.domain.model.util.Constant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RegisterUserUseCaseTest {

    private UserRepository userRepository;

    private RegisterUserUseCase useCase;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        useCase = new RegisterUserUseCase(userRepository);
    }

    @Test
    void register_usercase_null() {

        var response = useCase.registerUser(null);
        StepVerifier.create(response)
                .expectErrorMatches(err -> err instanceof BusinessException &&
                        err.getMessage().equals(Constant.MISSING_ARGUMENTS_DESCRIPTION))
                .verify();

    }

    @Test
    void register_usercase_ok() {

        doNothing().when(userRepository).saveUser(isA(User.class));
        var response = useCase.registerUser(getResquestDTO());
        StepVerifier.create(response)
                .consumeNextWith(Assertions::assertNotNull)
                .verifyComplete();

    }

    private static UserRegisterResquestDTO getResquestDTO() {

        UserRegisterResquestDTO requestDTO = new UserRegisterResquestDTO();
        requestDTO.setEmail("");
        requestDTO.setRole("");
        requestDTO.setGoogle(false);
        requestDTO.setName("");
        requestDTO.setImage("");
        requestDTO.setPassword(null);

        return  requestDTO;
    }
}