package com.openclassroom.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter
public class PersonMedicalrecordDTO {
    private Long personId;
    private String firstname;
    private String lastname;
    private String birthdate;
}
