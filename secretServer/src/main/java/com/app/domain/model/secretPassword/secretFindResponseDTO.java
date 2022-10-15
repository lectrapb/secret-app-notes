package com.app.domain.model.secretPassword;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class secretFindResponseDTO {
    private String id;
    private String name;
    private String username;
    private String password;
    private String uri;
    private int contador;

}
