package com.app.domain.usecases.secrets.secretNoteUseCase.mapper;

import com.app.config.BusinessException;
import com.app.domain.model.secretNote.secretNote;
import com.app.domain.model.secretNote.secretUpdateNoteRequestDTO;
import com.app.domain.model.util.Constant;

public class MapperUpdateNote {

    public static secretNote toUpdateNote(secretUpdateNoteRequestDTO requestDTO){
        secretNote updateNote = new secretNote();
        if(requestDTO.getId() == "" || requestDTO.getName() == ""){
            throw  new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE);
        } else if(requestDTO.getNotes().length() > 255){
            throw  new BusinessException(Constant.ERROR_EXCEED_LIMIT_CODE);
        }
        updateNote.setId(requestDTO.getId());
        updateNote.setName(requestDTO.getName());
        updateNote.setNotes(requestDTO.getNotes());

        return updateNote;
    }
}
