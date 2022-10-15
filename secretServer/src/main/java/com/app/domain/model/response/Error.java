package com.app.domain.model.response;


import lombok.Data;

@Data
public class Error {

    private final int status;
    private final String code;
    private final String title;
    private final String detail;

}
