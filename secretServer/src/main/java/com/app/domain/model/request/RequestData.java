package com.app.domain.model.request;

import lombok.Data;

@Data
public class RequestData <T>{

    private T[] data;
}
