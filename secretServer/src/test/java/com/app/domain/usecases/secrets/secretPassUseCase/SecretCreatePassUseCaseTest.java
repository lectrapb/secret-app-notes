package com.app.domain.usecases.secrets.secretPassUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.secretPassword.gateway.SecretPasswordRepository;
import com.app.domain.model.secretPassword.secretPassword;
import com.app.domain.model.secretPassword.secretPasswordRequestDTO;
import com.app.domain.model.token.gateway.EncryptService;
import org.jose4j.lang.JoseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.when;

class SecretCreatePassUseCaseTest {

    @InjectMocks
    private SecretCreatePassUseCase passUseCase;

    @Mock
    private SecretPasswordRepository secretPasswordRepository;

    @Mock
    private static EncryptService encryptService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenNull_addThrows(){
        var validate = passUseCase.registerPassword(getResquestNullDTO());
        StepVerifier.create(validate)
                .expectErrorMatches(e -> e instanceof BusinessException)
                .verify();
    }

    @Test
    void registerPassword() throws JoseException {
        when(secretPasswordRepository.save(isA(secretPassword.class))).thenReturn(Mono.empty());
        var register = passUseCase.registerPassword(getResquestDTO());
        StepVerifier.create(register)
                .consumeNextWith(Assertions::assertNotNull)
                .verifyComplete();
    }

    private static secretPasswordRequestDTO getResquestDTO() throws JoseException {

        secretPasswordRequestDTO requestDTO = new secretPasswordRequestDTO();
        requestDTO.setName("algo");
        requestDTO.setUsername("algo");
        requestDTO.setPassword(encryptService.encrypt(requestDTO.getPassword()));
        requestDTO.setURI("algo");
        requestDTO.setUser_uid("algo");

        return  requestDTO;
    }

    private static secretPasswordRequestDTO getResquestNullDTO() {

        secretPasswordRequestDTO requestDTO = new secretPasswordRequestDTO();
        requestDTO.setName(null);
        requestDTO.setUsername(null);
        requestDTO.setPassword(null);
        requestDTO.setURI(null);
        requestDTO.setURI(null);

        return  requestDTO;
    }
}