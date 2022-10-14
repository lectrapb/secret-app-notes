package com.app.domain.model.secretNote;

import lombok.Data;

@Data
public class secretFindNoteRequestDTO {
    private int page;
    private int rank;
    private String user;
}
