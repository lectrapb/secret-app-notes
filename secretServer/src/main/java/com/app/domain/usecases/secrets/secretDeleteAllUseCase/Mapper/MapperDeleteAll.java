package com.app.domain.usecases.secrets.secretDeleteAllUseCase.Mapper;

import com.app.domain.model.secretDeleteAll.secretAll;
import com.app.domain.model.secretDeleteAll.secretDeleteAllRequestDTO;

public class MapperDeleteAll {

    public static secretAll toDeleteAll(secretDeleteAllRequestDTO allRequestDTO){
        var secretDeleteAll = new secretAll();
        secretDeleteAll.setUser_uid(allRequestDTO.getId());
        return secretDeleteAll;
    }
}
