package com.openclassroom.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonInfoDTO {
    private Long personId;
    private String firstname;
    private String lastname;
    private String address;
    private String birthdate;
    private String email;
    private String medications;
    private String allergies;

}
