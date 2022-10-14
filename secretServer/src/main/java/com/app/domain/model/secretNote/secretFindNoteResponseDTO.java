package com.app.domain.model.secretNote;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class secretFindNoteResponseDTO {
    private String id;
    private String name;
    private String notes;
    private int contador;

}
