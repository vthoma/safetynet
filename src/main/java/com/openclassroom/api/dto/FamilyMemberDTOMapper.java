package com.openclassroom.api.dto;

import com.openclassroom.api.model.Person;
import com.openclassroom.api.dto.FamilyMemberDTO;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
@Generated
public interface FamilyMemberDTOMapper {

    FamilyMemberDTO personToFamilyMemberDTO(Person person);

    List<FamilyMemberDTO> personListToFamilyMemberDTOList(List<Person> personList);
}