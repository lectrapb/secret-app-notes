package com.app.domain.usecases.secrets.secretPassUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.secretPassword.gateway.SecretSearchPass;
import com.app.domain.model.secretPassword.secretFindRequestDTO;
import com.app.domain.model.secretPassword.secretFindResponseDTO;
import com.app.domain.model.secretPassword.secretPasswordRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

class SecretSearchUseCaseTest {

    @InjectMocks
    private SecretSearchUseCase searchUseCase;

    @Mock
    private SecretSearchPass secretSearchPass;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findPassword() {
        List<secretFindResponseDTO> list = Arrays.asList(
                new secretFindResponseDTO("id", "name", "username", "password", "uri", 1)
        );
        when(secretSearchPass.find(isA(secretFindRequestDTO.class))).thenReturn(Mono.just(list));
        var search = searchUseCase.findPassword(getResquestSearchDTO());
        StepVerifier.create(search)
                .consumeNextWith(Assertions::assertNotNull)
                .verifyComplete();
    }

    @Test
    public void givenNull_searchThrows(){
        var validate = searchUseCase.findPassword(getResquestSearchNullDTO());
        StepVerifier.create(validate)
                .expectErrorMatches(e -> e instanceof BusinessException)
                .verify();
    }

    private static secretFindRequestDTO getResquestSearchDTO() {

        secretFindRequestDTO requestDTO = new secretFindRequestDTO();
        requestDTO.setPage("1");
        requestDTO.setRank("5");
        requestDTO.setUser("1");

        return  requestDTO;
    }

    private static secretFindRequestDTO getResquestSearchNullDTO() {

        secretFindRequestDTO requestDTO = new secretFindRequestDTO();
        requestDTO.setPage(null);
        requestDTO.setRank(null);
        requestDTO.setUser(null);

        return  requestDTO;
    }
}