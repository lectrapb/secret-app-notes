package com.app.domain.usecases.secrets.secretNoteUseCase.mapper;



import com.app.domain.model.secretNote.secretNote;
import com.app.domain.model.secretNote.secretNoteRequestDTO;

import java.util.UUID;

public class MapperCreateNote {

    public static secretNote toSecretNote(secretNoteRequestDTO requestDTO){
        secretNote note = new secretNote();
        //validación si es null
        note.setId(UUID.randomUUID().toString());
        note.setName(requestDTO.getName());
        note.setNotes(requestDTO.getNotes());
        note.setUser_uid(requestDTO.getUser_uid());

        return note;
    }
}
