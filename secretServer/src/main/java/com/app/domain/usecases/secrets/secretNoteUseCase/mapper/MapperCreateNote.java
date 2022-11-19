package com.app.domain.usecases.secrets.secretNoteUseCase.mapper;



import com.app.config.BusinessException;
import com.app.domain.model.secretNote.secretNote;
import com.app.domain.model.secretNote.secretNoteRequestDTO;
import com.app.domain.model.util.Constant;

import java.util.UUID;

public class MapperCreateNote {

    public static secretNote toSecretNote(secretNoteRequestDTO requestDTO){
        secretNote note = new secretNote();
        if(requestDTO.getName() == "" || requestDTO.getName() == null){
            throw  new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE);
        } else if(requestDTO.getNotes() != null && requestDTO.getNotes().length() > 800){
            throw  new BusinessException(Constant.ERROR_EXCEED_LIMIT_CODE);
        }
        note.setId(UUID.randomUUID().toString());
        note.setName(requestDTO.getName());
        note.setNotes(requestDTO.getNotes());
        note.setUser_uid(requestDTO.getUser_uid());

        return note;
    }
}
