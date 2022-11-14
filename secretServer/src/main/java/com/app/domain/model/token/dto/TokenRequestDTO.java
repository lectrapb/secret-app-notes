package com.app.domain.model.token.dto;


import com.app.domain.model.util.ValueObject;
import lombok.Data;

@Data
public class TokenRequestDTO implements ValueObject {

    private String token;

    @Override
    public String value() {
        return token;
    }
}
