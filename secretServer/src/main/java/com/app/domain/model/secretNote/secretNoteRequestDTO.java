package com.app.domain.model.secretNote;

import lombok.Data;

@Data
public class secretNoteRequestDTO {
    private String name;
    private String notes;
    private String user_uid;
}
