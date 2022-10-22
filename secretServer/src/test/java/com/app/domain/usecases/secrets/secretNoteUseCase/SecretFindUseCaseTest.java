package com.app.domain.usecases.secrets.secretNoteUseCase;

import com.app.config.BusinessException;
import com.app.domain.model.secretNote.gateway.SecretFindNoteRepository;
import com.app.domain.model.secretNote.secretFindNoteRequestDTO;
import com.app.domain.model.secretNote.secretFindNoteResponseDTO;
import com.app.domain.model.secretPassword.secretFindRequestDTO;
import com.app.domain.model.secretPassword.secretFindResponseDTO;
import com.app.domain.model.secretPassword.secretUpdateRequestDTO;
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

class SecretFindUseCaseTest {

    @InjectMocks
    private SecretFindUseCase findUseCase;
    @Mock
    private SecretFindNoteRepository noteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findNote() {
        List<secretFindNoteResponseDTO> list = Arrays.asList(
                new secretFindNoteResponseDTO("id", "name", "notes", 1)
        );
        when(noteRepository.find(isA(secretFindNoteRequestDTO.class))).thenReturn(Mono.just(list));
        var search = findUseCase.findNote(getResquestFindNoteDTO());
        StepVerifier.create(search)
                .consumeNextWith(Assertions::assertNotNull)
                .verifyComplete();
    }

    @Test
    public void givenNull_searchThrows(){
        var validate = findUseCase.findNote(getResquestFindNoteNullDTO());
        StepVerifier.create(validate)
                .expectErrorMatches(e -> e instanceof BusinessException)
                .verify();
    }


    private static secretFindNoteRequestDTO getResquestFindNoteDTO() {

        secretFindNoteRequestDTO requestDTO = new secretFindNoteRequestDTO();
        requestDTO.setPage("1");
        requestDTO.setRank("5");
        requestDTO.setUser("1");
        return  requestDTO;
    }

    private static secretFindNoteRequestDTO getResquestFindNoteNullDTO() {

        secretFindNoteRequestDTO requestDTO = new secretFindNoteRequestDTO();
        requestDTO.setPage(null);
        requestDTO.setRank(null);
        requestDTO.setUser(null);

        return  requestDTO;
    }

}