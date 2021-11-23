package com.openclassroom.api.service;

import com.openclassroom.api.controller.PersonController;
import com.openclassroom.api.dto.FirestationDTO;
import com.openclassroom.api.dto.PersonInfoDTO;
import com.openclassroom.api.dto.PersonMedicalrecordDTO;
import com.openclassroom.api.model.Firestation;
import com.openclassroom.api.model.Medicalrecord;
import com.openclassroom.api.model.Person;
import com.openclassroom.api.repository.FirestationRepository;
import com.openclassroom.api.repository.MedicalrecordRepository;
import com.openclassroom.api.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.ParseException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
class PersonServiceTest {

    @InjectMocks
    private final PersonController personController;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonService personService;

    @Mock
    private FirestationService firestationService;

    @Mock
    private FirestationRepository firestationRepository;

    @Mock
    private MedicalrecordService medicalrecordService;

    @Mock
    private MedicalrecordRepository medicalrecordRepository;

    @Test
    public void getAllPersonsMedicalTest() throws ParseException {
        Medicalrecord mrp1 = new Medicalrecord(1L, "John", "Boyd", "03/06/1984", "test", "");
        Medicalrecord mrp2 = new Medicalrecord(1L, "Jacob", "Boyd", "03/06/1989", "", "test");
        Medicalrecord mrp3 = new Medicalrecord(1L, "Tenley", "Boyd", "02/18/2012", "", "");
        Person p1 = new Person(1L, "John", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-6512", "jaboyd@email.com", mrp1);
        Person p2 = new Person(1L, "Jacob", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-6513", "drk@email.com", mrp2);
        Person p3 = new Person(1L, "Tenley", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-6512", "tenz@email.com", mrp3);
        List<Person> person = new ArrayList<>();
        person.add(p1);
        person.add(p2);
        person.add(p3);
        lenient().when(personService.getPersonsFromAddress(any())).thenReturn(person);
        List<Person> list = personController.getAllPersonsMedical("1509 Culver St");
        assertEquals(1, list.size());
    }

    @Test
    public void getUserEmailByCityTest() {
        Medicalrecord mrp1 = new Medicalrecord(1L, "John", "Boyd", "03/06/1984", "test", "");
        Medicalrecord mrp2 = new Medicalrecord(1L, "Jacob", "Boyd", "03/06/1989", "", "test");
        Medicalrecord mrp3 = new Medicalrecord(1L, "Tenley", "Boyd", "02/18/2012", "", "");
        Person p1 = new Person(1L, "John", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-6512", "jaboyd@email.com", mrp1);
        Person p2 = new Person(1L, "Jacob", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-6513", "drk@email.com", mrp2);
        Person p3 = new Person(1L, "Tenley", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-6512", "tenz@email.com", mrp3);
        List<Person> persons = new ArrayList<>();
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);
        lenient().when(personService.getPersonByCity(any())).thenReturn(persons);
        List<String> list = personController.getUserEmailByCity("Culver");
        assertEquals("jaboyd@email.com", list.get(0));
    }

    @Test
    public void getPersonsByAddressFireTest() {
        Medicalrecord mrp1 = new Medicalrecord(1L, "John", "Boyd", "03/06/1984", "test", "");
        Medicalrecord mrp2 = new Medicalrecord(1L, "Jacob", "Boyd", "03/06/1989", "", "test");
        Medicalrecord mrp3 = new Medicalrecord(1L, "Tenley", "Boyd", "02/18/2012", "", "");
        Person p1 = new Person(1L, "John", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-6512", "jaboyd@email.com", mrp1);
        Person p2 = new Person(1L, "Jacob", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-6513", "drk@email.com", mrp2);
        Person p3 = new Person(1L, "Tenley", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-6512", "tenz@email.com", mrp3);
        List<Person> persons = new ArrayList<>();
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);
        when(personService.getPersonsFromAddress(anyString())).thenReturn(persons);
        List<Person> list = personController.getPersonsByAddressFire("1509 Culver St");
        assertEquals(3, list.size());
    }


    @Test
    public void getPersonByFirestationIdTest() {
        Firestation fs1 = new Firestation(1L, "1509 Culver St", 1);
        Firestation fs2 = new Firestation(1L, "644 Gershwin Cir", 2);
        Medicalrecord mrp1 = new Medicalrecord(1L, "John", "Boyd", "03/06/1984", "test", "");
        Medicalrecord mrp2 = new Medicalrecord(1L, "Jacob", "Boyd", "03/06/1989", "", "test");
        Medicalrecord mrp3 = new Medicalrecord(1L, "Tenley", "Boyd", "02/18/2012", "", "");
        Person p1 = new Person(1L, "John", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-6512", "jaboyd@email.com", mrp1);
        Person p2 = new Person(1L, "Jacob", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-6513", "drk@email.com", mrp2);
        Person p3 = new Person(1L, "Tenley", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-6512", "tenz@email.com", mrp3);
        List<Person> personList = new ArrayList<>();
        List<Firestation> firestations = new ArrayList<>();
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);
        firestations.add(fs1);
        firestations.add(fs2);
        when(personService.getPersonsFromAddress(anyString())).thenReturn(personList);
        when(medicalrecordService.getPersonByFullName(anyString(), anyString())).thenReturn(Optional.of(mrp1));
        when(firestationService.getFirestationByStation(anyInt())).thenReturn(firestations);
        FirestationDTO fire = personController.getPersonByFirestationId(1);
        assertEquals(0, fire.getCountChild());
    }

    @Test
    public void getPhoneAlert() {
        Firestation fs1 = new Firestation(1L, "1509 Culver St", 1);
        Person p1 = new Person(1L, "John", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-6512", "jaboyd@email.com", null);
        List<Person> lst = new ArrayList<>();
        lst.add(p1);
        when(firestationService.getFirestationByStation(any())).thenReturn(Collections.singletonList(fs1));
        when(personService.getPersonsFromAddress(any())).thenReturn(lst);
        List<String> list = personController.getPhoneAlert(1);
        assertEquals(1, list.size());
    }
}