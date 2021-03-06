package com.openclassroom.api.controller;

import com.openclassroom.api.dto.*;
import com.openclassroom.api.model.Firestation;
import com.openclassroom.api.model.Person;
import com.openclassroom.api.service.FirestationService;
import com.openclassroom.api.service.MedicalrecordService;
import com.openclassroom.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.util.*;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private MedicalrecordService medicalrecordService;

    @Autowired
    private FirestationService firestationService;

    @GetMapping("fire")
    public List<Person> getPersonsByAddressFire(@RequestParam(name = "address") String address) {
        List<Person> list = personService.getPersonsFromAddress(address);
        return list;
    }

    @GetMapping("/persons")
    public List<Person> getAllPersons(){
        return personService.getPersons();
    }
    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }

//    @PutMapping("/person")
//    public Person updatePerson(@RequestBody Person person) {
//        return personService.updatePerson(person);
//    }

    @DeleteMapping("/person")
    public Integer deletePerson(@RequestParam String firstname, @RequestParam String lastname) {
        Integer deleteResult = personService.deletePerson(firstname, lastname);
        return deleteResult;
    }

    @GetMapping("/childAlert")
    public List<ChildalertDTO> getChildAlertList(@RequestParam String address) {

        List<ChildalertDTO> childAlertDTOList = personService.getChildAlertDTOListFromAddress(address);
        return childAlertDTOList;

    }

    @GetMapping("personInfo")
    public List<PersonInfoDTO> getPersonInfo(@RequestParam(name = "firstName") String firstname, @RequestParam(name = "lastName") String lastname) {
        return personService.getPersonByFullName(firstname, lastname);
    }

    @GetMapping("communityEmail")
    public List<String> getUserEmailByCity(@RequestParam(name = "city") String city) {
        List<Person> persons = personService.getPersonByCity(city);
        List<String> list = new ArrayList();
        for(Person person : persons) {
            list.add(person.getEmail());
        }
        return list;
    }
    @GetMapping("phoneAlert")
    public List<String> getPhoneAlert(@RequestParam(name = "firestation") final int stationNumber) {
        List<Firestation> firestation = firestationService.getFirestationByStation(stationNumber);
        List<Person> personList = new ArrayList<>();
        List<String> person = new ArrayList<>();
        for (Firestation fire : firestation) {
            personList.addAll(personService.getPersonsFromAddress(fire.getAddress()));
        }
        for (Person p : personList) {
            person.add(p.getPhone());
        }
        return person;
    }

    @GetMapping("firestation")
    public FirestationDTO getPersonByFirestationId(@RequestParam(name = "stationNumber") int stationNumber){
        List<Firestation> firestations = firestationService.getFirestationByStation(stationNumber);
        List<Person> personList = new ArrayList<>();
        for (Firestation fire : firestations) {
            personList.addAll(personService.getPersonsFromAddress(fire.getAddress()));
        }
        FirestationDTO firestationDTO = new FirestationDTO();
        for (Person p : personList) {
            firestationDTO.setData(p.getFirstName(), p.getLastName());
        }
        for (Data d : firestationDTO.getData()) {
            if (Integer.parseInt(medicalrecordService.getPersonByFullName(d.getFirstname(), d.getLastname()).get().getBirthDate().toString().substring(0, 4)) <= 2003) {
                firestationDTO.setCountAdult(firestationDTO.getCountAdult() +1);
            } else {
                firestationDTO.setCountChild(firestationDTO.getCountChild() +1);
            }
        }
        return firestationDTO;
    }
}
