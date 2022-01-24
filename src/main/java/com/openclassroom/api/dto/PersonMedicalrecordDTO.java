package com.openclassroom.api.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Generated
@Getter
@Setter
public class PersonMedicalrecordDTO {
    private Long personId;
    private String firstname;
    private String lastname;
    private String birthdate;
}
