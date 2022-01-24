package com.openclassroom.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class ChildalertDTO {
    private String lastName;

    private String firstName;

    private int age = Integer.MIN_VALUE;

    private List<FamilyMemberDTO> familyMembers= new ArrayList<>();
}