package com.app.domain.model.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRemoveResponseDTO {

    private String code;
}
