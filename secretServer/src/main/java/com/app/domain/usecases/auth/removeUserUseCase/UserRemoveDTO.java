package com.app.domain.usecases.auth.removeUserUseCase;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRemoveDTO {

    private String code;
}
