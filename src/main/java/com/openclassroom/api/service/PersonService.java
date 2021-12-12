package com.openclassroom.api.service;

import java.util.List;
import java.util.stream.Collectors;

import com.openclassroom.api.dto.PersonInfoDTO;
import com.openclassroom.api.model.Person;
import com.openclassroom.api.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter
@Setter
@Service
public class PersonService {

    private static final Logger logger = LogManager.getLogger(PersonService.class);

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private FirestationService firestationService;

//    public Optional<Person> getPerson(final Long id) {
//        return personRepository.findById(id);
//    }
//
//    public List<Person> getPersons() {
//        return personRepository.findAll();
//    }

    public List<Person> getPersonByCity(final String city) {
        return personRepository.findByCity(city);
    }

    public List<PersonInfoDTO> getPersonByFullName(final String firstname, final String lastname) {
        return personRepository.findByFirstNameAndLastName(firstname, lastname)
                .stream()
                .map(this::personInfoDTO)
                .collect(Collectors.toList());
    }

    public List<Person> getPersonsFromAddress(final String address) {
        List<Person> list = personRepository.findByAddress(address);
        return list;
    }

    public PersonInfoDTO personInfoDTO(Person person) {
        PersonInfoDTO personInfoDTO = new PersonInfoDTO();
        personInfoDTO.setLastname(person.getLastName());
        personInfoDTO.setBirthdate(person.getMedicalrecord().getBirthdate());
        personInfoDTO.setAddress(person.getAddress());
        personInfoDTO.setAllergiesList(person.getMedicalrecord().getAllergies());
        personInfoDTO.setMedicationList(person.getMedicalrecord().getMedications());
        personInfoDTO.setEmail(person.getEmail());
        return personInfoDTO;
    }

//    public void deletePerson(final Long id) {
//        personRepository.deleteById(id);
//    }

    public boolean saveAllPersons(List<Person> personList) {

        if (personList != null) {
            try {
                personRepository.saveAll(personList);
                return true;
            } catch (Exception exception) {
                logger.error("Erreur lors de l'enregistrement de la liste des personnes " + exception.getMessage() + " , Stack Trace : " + exception.getStackTrace());
            }
        }
        return false;
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

}