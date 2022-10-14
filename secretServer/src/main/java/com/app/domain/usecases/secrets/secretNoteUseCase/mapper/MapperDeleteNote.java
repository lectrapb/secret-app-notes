package com.app.domain.usecases.secrets.secretNoteUseCase.mapper;

import com.app.domain.model.secretNote.secretDeleteNoteRequestDTO;
import com.app.domain.model.secretNote.secretNote;

public class MapperDeleteNote {

    public static secretNote toDeletePass(secretDeleteNoteRequestDTO requestDTO){
        //exception
        secretNote deleteNote = new secretNote();

        deleteNote.setId(requestDTO.getId());
        return  deleteNote;
    }

}
