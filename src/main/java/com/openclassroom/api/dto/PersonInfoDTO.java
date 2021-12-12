package com.openclassroom.api.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter
@Generated
@EqualsAndHashCode
public class PersonInfoDTO {
    private String firstname;
    private String lastname;
    private String address;
    private String birthdate;
    private String email;
    private List<String> medicationList = new ArrayList<>();
    private List<String> allergiesList = new ArrayList<>();

}
