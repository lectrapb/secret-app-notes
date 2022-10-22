package com.app.domain.usecases.secrets.secretPassUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.secretPassword.gateway.SecretUpdatePassRepository;
import com.app.domain.model.secretPassword.secretFindRequestDTO;
import com.app.domain.model.secretPassword.secretPassword;
import com.app.domain.model.secretPassword.secretUpdateRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

class SecretUpdateUseCaseTest {

    @InjectMocks
    private SecretUpdateUseCase updateUseCase;
    @Mock
    private SecretUpdatePassRepository updatePassRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updatePassword() {
        when(updatePassRepository.update(isA(secretPassword.class))).thenReturn(Mono.just("123"));
        var update = updateUseCase.updatePassword(getResquestUpdateDTO());
        StepVerifier.create(update)
                .consumeNextWith(Assertions::assertNotNull)
                .verifyComplete();
    }

    @Test
    public void givenNull_updateThrows(){
        var validate = updateUseCase.updatePassword(getResquestUpdateNullDTO());
        StepVerifier.create(validate)
                .expectErrorMatches(e -> e instanceof BusinessException)
                .verify();
    }

    private static secretUpdateRequestDTO getResquestUpdateDTO() {

        secretUpdateRequestDTO requestDTO = new secretUpdateRequestDTO();
        requestDTO.setId("123");
        requestDTO.setName("algo");
        requestDTO.setUsername("algo");
        requestDTO.setPassword("algo");
        requestDTO.setURI("localhost");
        return  requestDTO;
    }

    private static secretUpdateRequestDTO getResquestUpdateNullDTO() {

        secretUpdateRequestDTO requestDTO = new secretUpdateRequestDTO();
        requestDTO.setId(null);
        requestDTO.setName(null);
        requestDTO.setUsername("algo");
        requestDTO.setPassword("algo");
        requestDTO.setURI("localhost");

        return  requestDTO;
    }
}