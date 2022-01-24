package com.openclassroom.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.openclassroom.api.dto.ChildAlertDTOMapper;
import com.openclassroom.api.dto.FamilyMemberDTOMapper;
import com.openclassroom.api.dto.PersonInfoDTO;
import com.openclassroom.api.dto.ChildalertDTO;
import com.openclassroom.api.model.Medicalrecord;
import com.openclassroom.api.model.Person;
import com.openclassroom.api.repository.MedicalrecordRepository;
import com.openclassroom.api.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.Generated;
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
@Generated
public class PersonService {

    private static final Logger logger = LogManager.getLogger(PersonService.class);

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private FirestationService firestationService;

    @Autowired
    private MedicalrecordService medicalrecordService;

    @Autowired
    private MedicalrecordRepository medicalrecordRepository;

    @Autowired
    private ChildAlertDTOMapper childAlertDTOMapper;

    @Autowired
    private FamilyMemberDTOMapper familyMemberDTOMapper;

//    public Optional<Person> getPerson(final Long id) {
//        return personRepository.findById(id);
//    }
//
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

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
        personInfoDTO.setBirthdate(medicalrecordService.getPersonByFullName(person.getFirstName(), person.getLastName()).get().getBirthDate().toString());
        personInfoDTO.setAddress(person.getAddress());
        personInfoDTO.setAllergiesList(medicalrecordService.getPersonByFullName(person.getFirstName(), person.getLastName()).get().getAllergies());
        personInfoDTO.setMedicationList(medicalrecordService.getPersonByFullName(person.getFirstName(), person.getLastName()).get().getMedications());
        personInfoDTO.setEmail(person.getEmail());
        return personInfoDTO;
    }


    private List<Person> findFamilyMembers(List<Person> personList, Person person)
    {
        return personList.stream().
                filter(personIterator -> personIterator.getAddress().equalsIgnoreCase(person.getAddress()) &&
                        !(personIterator.getFirstName().equalsIgnoreCase(person.getFirstName()) && personIterator.getLastName().equalsIgnoreCase(person.getLastName()))).
                collect(Collectors.toList());
    }

    public List<ChildalertDTO> getChildAlertDTOListFromAddress(String address) {
        if (address != null) {

            List<Person> personList = personRepository.findAllByAddressIgnoreCase(address);

            List<ChildalertDTO> childAlertDTOList = new ArrayList<>();

            //on parcourt la liste des personnes pour constituer les DTO à retourner, uniquement s'ils ont un dossier médical existant (sinon impossible de calculer leur âge)
            personList.forEach(personIterator -> {

                Optional<Medicalrecord> medicalRecordLinkedToPersonIterator = medicalrecordRepository.findByFirstNameAndLastName(personIterator.getFirstName(), personIterator.getLastName());

                if (medicalRecordLinkedToPersonIterator.isPresent()) {
                    // si le dossier médical existe, on peut créer le DTO correspondant à cette personne et ce dossier médical

                    ChildalertDTO childAlertDTO = childAlertDTOMapper.convertToChildAlertDTO(personIterator, medicalRecordLinkedToPersonIterator.get());

                    //on récupère les membres du foyer pour la personne en cours
                    List<Person> personFamilyMembers = findFamilyMembers(personList,personIterator);

                    childAlertDTO.setFamilyMembers(familyMemberDTOMapper.personListToFamilyMemberDTOList(personFamilyMembers));

                    childAlertDTOList.add(childAlertDTO);
                }
            });

            //on ne retourne que les éléments chilAlert dont l'age est <=18 ans
            return childAlertDTOList.stream().filter(childAlertDTO -> childAlertDTO.getAge() <= 18).collect(Collectors.toList());

        }
        return null;

    }

    public Integer deletePerson(String firstname, String lastname) {
        return personRepository.deletePersonByFirstNameAndLastNameAllIgnoreCase(firstname, lastname);
    }

    public boolean saveAllPersons(List<Person> personList) {
        personRepository.saveAll(personList);
        return true;
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

}