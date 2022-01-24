package com.openclassroom.api.dto;

import com.openclassroom.api.model.Medicalrecord;
import com.openclassroom.api.model.Person;
import com.openclassroom.api.utils.DateUtils;
import lombok.Generated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;


import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Mapper(componentModel = "spring", uses = FamilyMemberDTOMapper.class)
@Generated
public abstract class ChildAlertDTOMapper {

    @Mappings({
            @Mapping(target="lastName", source="person.lastName"),
            @Mapping(target="firstName", source="person.firstName"),
            @Mapping(target="age", source="medicalRecord.birthDate", qualifiedByName="calculateAge"),
    })
    public abstract ChildalertDTO convertToChildAlertDTO(Person person, Medicalrecord medicalRecord);

    @Autowired
    DateUtils dateUtils;

    @Named("calculateAge")
    public int getAge(LocalDate birthDate) {
        return dateUtils.getAge(birthDate);
    }
}