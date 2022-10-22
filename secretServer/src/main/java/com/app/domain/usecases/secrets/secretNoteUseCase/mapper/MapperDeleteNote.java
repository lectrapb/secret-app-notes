package com.app.domain.usecases.secrets.secretNoteUseCase.mapper;

import com.app.config.BusinessException;
import com.app.domain.model.secretNote.secretDeleteNoteRequestDTO;
import com.app.domain.model.secretNote.secretNote;
import com.app.domain.model.util.Constant;

public class MapperDeleteNote {

    public static secretNote toDeletePass(secretDeleteNoteRequestDTO requestDTO){
        secretNote deleteNote = new secretNote();
        if(requestDTO.getId() == "" || requestDTO.getId() == null){
            throw  new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE);
        }
        deleteNote.setId(requestDTO.getId());
        return  deleteNote;
    }

}
