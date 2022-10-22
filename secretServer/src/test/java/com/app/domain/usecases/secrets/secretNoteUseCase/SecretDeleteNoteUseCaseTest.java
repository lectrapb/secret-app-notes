package com.app.domain.usecases.secrets.secretNoteUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.secretNote.gateway.SecretDeleteNoteRepository;
import com.app.domain.model.secretNote.secretDeleteNoteRequestDTO;
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

class SecretDeleteNoteUseCaseTest {

    @InjectMocks
    private SecretDeleteNoteUseCase deleteNoteUseCase;
    @Mock
    private SecretDeleteNoteRepository deleteNoteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deleteNote() {
        when(deleteNoteRepository.delete(isA(String.class))).thenReturn(Mono.just("123"));
        var update = deleteNoteUseCase.deleteNote(getResquestDeleteNoteDTO());
        StepVerifier.create(update)
                .consumeNextWith(Assertions::assertNotNull)
                .verifyComplete();
    }

    @Test
    public void givenNull_deleteNoteThrows(){
        var validate = deleteNoteUseCase.deleteNote(getResquestDeleteNoteNullDTO());
        StepVerifier.create(validate)
                .expectErrorMatches(e -> e instanceof BusinessException)
                .verify();
    }

    private static secretDeleteNoteRequestDTO getResquestDeleteNoteDTO() {

        secretDeleteNoteRequestDTO requestDTO = new secretDeleteNoteRequestDTO();
        requestDTO.setId("123");
        return  requestDTO;
    }

    private static secretDeleteNoteRequestDTO getResquestDeleteNoteNullDTO() {

        secretDeleteNoteRequestDTO requestDTO = new secretDeleteNoteRequestDTO();
        requestDTO.setId(null);
        return  requestDTO;
    }
}