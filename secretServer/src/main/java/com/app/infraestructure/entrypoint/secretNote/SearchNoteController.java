package com.app.infraestructure.entrypoint.secretNote;

import com.app.domain.model.response.ApiResponse;
import com.app.domain.model.secretNote.secretFindNoteRequestDTO;
import com.app.domain.model.util.Constant;
import com.app.domain.usecases.secrets.secretNoteUseCase.SecretFindUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class SearchNoteController {

    @Autowired
    private SecretFindUseCase secretFindUseCase;

    @PostMapping(Constant.PATH_SECRET_NOTE_SELECT)
    public Mono<ResponseEntity<ApiResponse>> noteSelect(@RequestBody secretFindNoteRequestDTO noteRequestDTO){
        return Mono.fromCallable(()-> noteRequestDTO)
                .flatMap(secretFindUseCase::findNote)
                .map(p -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(new ApiResponse()
                                .createOnSuccess()
                                .setMessage(Constant.SUCCESSFUL_SECRET_NOTE_CODE)
                                .setData(Constant.NOTE_SELECT, p,"SELECT")))
                .onErrorResume(e -> Mono.just(e)
                        .flatMap(t ->{
                            ApiResponse apiResponse = new ApiResponse().createOnError(t.getMessage());
                            return Mono.just(ResponseEntity
                                    .badRequest()
                                    .body(apiResponse));
                        }));
    }
}
