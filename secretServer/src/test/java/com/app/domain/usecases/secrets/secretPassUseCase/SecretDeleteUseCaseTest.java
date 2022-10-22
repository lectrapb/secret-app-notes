package com.app.domain.usecases.secrets.secretPassUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.secretPassword.gateway.SecretDeletePassRepository;
import com.app.domain.model.secretPassword.secretDeleteRequestDTO;
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


class SecretDeleteUseCaseTest {

    @InjectMocks
    private SecretDeleteUseCase deleteUseCase;
    @Mock
    private SecretDeletePassRepository deletePassRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deletePassword() {
        when(deletePassRepository.delete(isA(String.class))).thenReturn(Mono.just("123"));
        var delete = deleteUseCase.deletePassword(getResquestDeleteDTO());
        StepVerifier.create(delete)
                .consumeNextWith(Assertions::assertNotNull)
                .verifyComplete();
    }

    @Test
    public void givenNull_deleteThrows(){
        var validate = deleteUseCase.deletePassword(getResquestDeleteNullDTO());
        StepVerifier.create(validate)
                .expectErrorMatches(e -> e instanceof BusinessException)
                .verify();
    }

    private static secretDeleteRequestDTO getResquestDeleteDTO() {

        secretDeleteRequestDTO requestDTO = new secretDeleteRequestDTO();
        requestDTO.setId("1234");

        return  requestDTO;
    }

    private static secretDeleteRequestDTO getResquestDeleteNullDTO() {

        secretDeleteRequestDTO requestDTO = new secretDeleteRequestDTO();
        requestDTO.setId(null);

        return  requestDTO;
    }
}