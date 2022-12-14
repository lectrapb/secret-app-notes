package com.app.domain.usecases.secrets.secretNoteUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.secretNote.gateway.SecretUpdateNoteRepository;
import com.app.domain.model.secretNote.secretNote;
import com.app.domain.model.secretNote.secretUpdateNoteRequestDTO;
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

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

class SecretUpdateNoteUseCaseTest {

    @InjectMocks
    private SecretUpdateNoteUseCase updateNoteUseCase;
    @Mock
    private SecretUpdateNoteRepository updateNoteRepository;
    @Mock
    private static EncryptService encryptService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateNote() throws JoseException {
        when(updateNoteRepository.update(isA(secretNote.class))).thenReturn(Mono.just("123"));
        var update = updateNoteUseCase.updateNote(getResquestUpdateNoteDTO());
        StepVerifier.create(update)
                .consumeNextWith(Assertions::assertNotNull)
                .verifyComplete();
    }

    @Test
    public void givenNull_updateNoteThrows(){
        var validate = updateNoteUseCase.updateNote(getResquestUpdateNoteNullDTO());
        StepVerifier.create(validate)
                .expectErrorMatches(e -> e instanceof BusinessException)
                .verify();
    }

    private static secretUpdateNoteRequestDTO getResquestUpdateNoteDTO() throws JoseException {

        secretUpdateNoteRequestDTO requestDTO = new secretUpdateNoteRequestDTO();
        requestDTO.setId("123");
        requestDTO.setName("algo");
        requestDTO.setNotes(encryptService.encrypt(requestDTO.getNotes()));
        return  requestDTO;
    }

    private static secretUpdateNoteRequestDTO getResquestUpdateNoteNullDTO() {

        secretUpdateNoteRequestDTO requestDTO = new secretUpdateNoteRequestDTO();
        requestDTO.setId(null);
        requestDTO.setName(null);
        requestDTO.setNotes("algo");

        return  requestDTO;
    }
}