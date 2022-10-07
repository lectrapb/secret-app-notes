package com.app.domain.model.secretPassword;

import lombok.Data;

@Data
public class secretFindRequestDTO {
    private int page;
    private int rank;
    private String user;
}
