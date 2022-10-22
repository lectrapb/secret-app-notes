package com.app.domain.usecases.secrets.secretNoteUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.secretNote.gateway.SecretCreateNoteRepository;
import com.app.domain.model.secretNote.secretNote;
import com.app.domain.model.secretNote.secretNoteRequestDTO;
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

class SecretCreateUseCaseTest {

    @InjectMocks
    private SecretCreateUseCase createUseCase;
    @Mock
    private SecretCreateNoteRepository secretCreateNoteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerNote() {
        when(secretCreateNoteRepository.save(isA(secretNote.class))).thenReturn(Mono.empty());
        var update = createUseCase.registerNote(getResquestCreateDTO());
        StepVerifier.create(update)
                .consumeNextWith(Assertions::assertNotNull)
                .verifyComplete();
    }

    @Test
    public void givenNull_createNoteThrows(){
        var validate = createUseCase.registerNote(getResquestCreateNoteNullDTO());
        StepVerifier.create(validate)
                .expectErrorMatches(e -> e instanceof BusinessException)
                .verify();
    }

    private static secretNoteRequestDTO getResquestCreateDTO() {

        secretNoteRequestDTO requestDTO = new secretNoteRequestDTO();
        requestDTO.setName("algo");
        requestDTO.setNotes("lasdl");
        requestDTO.setUser_uid("1");
        return  requestDTO;
    }

    private static secretNoteRequestDTO getResquestCreateNoteNullDTO() {

        secretNoteRequestDTO requestDTO = new secretNoteRequestDTO();
        requestDTO.setName(null);
        requestDTO.setNotes(null);
        requestDTO.setUser_uid(null);
        return  requestDTO;
    }
}