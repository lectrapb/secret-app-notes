package com.app.domain.model.response;

@lombok.Data
public class Data <T>{

    private final String type;
    private final String id;
    public final T atributes;
}
