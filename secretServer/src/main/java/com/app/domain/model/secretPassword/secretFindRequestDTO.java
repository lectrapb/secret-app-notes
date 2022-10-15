package com.app.domain.model.secretPassword;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class secretFindRequestDTO {
    private String page;
    private String rank;
    private String user;
}
