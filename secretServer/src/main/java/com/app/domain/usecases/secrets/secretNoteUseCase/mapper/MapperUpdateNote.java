package com.app.domain.usecases.secrets.secretNoteUseCase.mapper;

import com.app.domain.model.secretNote.secretNote;
import com.app.domain.model.secretNote.secretUpdateNoteRequestDTO;

public class MapperUpdateNote {

    public static secretNote toUpdateNote(secretUpdateNoteRequestDTO requestDTO){
        secretNote updateNote = new secretNote();

        updateNote.setId(requestDTO.getId());
        updateNote.setName(requestDTO.getName());
        updateNote.setNotes(requestDTO.getNotes());

        return updateNote;
    }
}
