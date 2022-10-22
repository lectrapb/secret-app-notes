package com.app.domain.usecases.secrets.verifyPassUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.secretNote.secretNote;
import com.app.domain.model.secretNote.secretUpdateNoteRequestDTO;
import com.app.domain.model.verifyPass.VerifyPassRequestDTO;
import com.app.domain.model.verifyPass.gateway.VerifyPass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

class VerifyPasswordUseCaseTest {

    @InjectMocks
    private VerifyPasswordUseCase verifyPasswordUseCase;
    @Mock
    private VerifyPass verifyPass;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void verifyPassword() {
        when(verifyPass.validatePass(isA(String.class))).thenReturn(Mono.just("123"));
        var update = verifyPasswordUseCase.verifyPassword(getResquestVerifyPassDTO());
        StepVerifier.create(update)
                .consumeNextWith(Assertions::assertNotNull)
                .verifyComplete();
    }

    @Test
    public void givenNull_verifyNoteThrows(){
        var validate = verifyPasswordUseCase.verifyPassword(getResquestVerifyNullDTO());
        StepVerifier.create(validate)
                .expectErrorMatches(e -> e instanceof BusinessException)
                .verify();
    }

    private static VerifyPassRequestDTO getResquestVerifyPassDTO() {

        VerifyPassRequestDTO requestDTO = new VerifyPassRequestDTO();
        requestDTO.setPassword("1234");
        return  requestDTO;
    }

    private static VerifyPassRequestDTO getResquestVerifyNullDTO() {

        VerifyPassRequestDTO requestDTO = new VerifyPassRequestDTO();
        requestDTO.setPassword(null);

        return  requestDTO;
    }

}