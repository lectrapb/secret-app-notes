package com.app.domain.model.response;

import lombok.Data;

@Data
public class Message {

    private final String source;
    private final String detail;
    private final String type;
    private final String title;
    private final int status;

}
