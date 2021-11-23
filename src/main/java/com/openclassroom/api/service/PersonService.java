package com.openclassroom.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.openclassroom.api.dto.FirestationDTO;
import com.openclassroom.api.dto.PersonInfoDTO;
import com.openclassroom.api.dto.PersonMedicalrecordDTO;
import com.openclassroom.api.dto.PhoneAlert;
import com.openclassroom.api.model.Firestation;
import com.openclassroom.api.model.Person;
import com.openclassroom.api.repository.FirestationRepository;
import com.openclassroom.api.repository.PersonRepository;
import com.openclassroom.api.service.FirestationService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import scala.Int;

@AllArgsConstructor
@Getter
@Setter
@Service
public class PersonService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PersonRepository personRepository;
    private FirestationRepository firestationRepository;
    private FirestationService firestationService;

    public Optional<Person> getPerson(final Long id) {
        return personRepository.findById(id);
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public List<Person> getPersonByCity(final String city) {
        return personRepository.findByCity(city);
    }

    public List<PersonInfoDTO> getPersonByFullName(final String firstname, final String lastname) {
        return personRepository.findByFirstnameAndLastname(firstname, lastname)
                .stream()
                .map(this::personInfoDTO)
                .collect(Collectors.toList());
    }

    public List<Person> getPersonsFromAddress(final String address) {
        List<Person> list = personRepository.findByAddress(address);
        return list;
    }
    public PhoneAlert phoneAlert(Person person) {
        PhoneAlert phoneAlert = new PhoneAlert();
        phoneAlert.setPhone(person.getPhone());
        return phoneAlert;
    }
    public PersonInfoDTO personInfoDTO(Person person) {
        PersonInfoDTO personInfoDTO = new PersonInfoDTO();
        personInfoDTO.setPersonId(person.getId());
        personInfoDTO.setLastname(person.getLastname());
        personInfoDTO.setBirthdate(person.getMedicalrecord().getBirthdate());
        personInfoDTO.setAddress(person.getAddress());
        personInfoDTO.setAllergies(person.getMedicalrecord().getAllergies());
        personInfoDTO.setMedications(person.getMedicalrecord().getMedications());
        personInfoDTO.setEmail(person.getEmail());
        return personInfoDTO;
    }

    public void deletePerson(final Long id) {
        personRepository.deleteById(id);
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

}