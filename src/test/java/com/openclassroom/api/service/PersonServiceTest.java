package com.openclassroom.api.service;

import com.openclassroom.api.controller.PersonController;
import com.openclassroom.api.dto.*;
import com.openclassroom.api.model.Firestation;
import com.openclassroom.api.model.Medicalrecord;
import com.openclassroom.api.model.Person;
import com.openclassroom.api.repository.FirestationRepository;
import com.openclassroom.api.repository.MedicalrecordRepository;
import com.openclassroom.api.repository.PersonRepository;
import com.openclassroom.api.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.text.ParseException;
import java.time.LocalDate;
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

    @Mock
    private ChildalertDTO childalertDTO;

    @Mock
    private ChildAlertDTOMapper childAlertDTOMapper;

    @SpyBean
    private DateUtils dateUtils;

    @Test
    public void getAllPersonsMedicalTest() throws ParseException {
        ChildalertDTO child1 = new ChildalertDTO("Jane", "Doe", 15, null);
        Medicalrecord mrp1 = new Medicalrecord("John", "Boyd", LocalDate.parse("2000-06-03"), null, null);
        Person p1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
        List<Person> person = new ArrayList<>();
        person.add(p1);
        lenient().when(childAlertDTOMapper.convertToChildAlertDTO(p1, mrp1)).thenReturn(child1);
        assertEquals(15, child1.getAge());
    }

    @Test
    public void getUserEmailByCityTest() {
        Medicalrecord mrp1 = new Medicalrecord("John", "Boyd", LocalDate.parse("1984-06-03"), null, null);
        Medicalrecord mrp2 = new Medicalrecord("Jacob", "Boyd", LocalDate.parse("1989-06-03"), null, null);
        Medicalrecord mrp3 = new Medicalrecord("Tenley", "Boyd", LocalDate.parse("2012-06-02"), null, null);
        Person p1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
        Person p2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513", "drk@email.com");
        Person p3 = new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "tenz@email.com");
        List<Person> persons = new ArrayList<>();
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);
        when(personService.getPersonByCity(anyString())).thenReturn(persons);
        List<String> list = personController.getUserEmailByCity("Culver");
        assertEquals("jaboyd@email.com", list.get(0));
    }

    @Test
    public void getPersonsByAddressFireTest() {
        Medicalrecord mrp1 = new Medicalrecord("John", "Boyd", LocalDate.parse("1984-06-03"), null, null);
        Medicalrecord mrp2 = new Medicalrecord("Jacob", "Boyd", LocalDate.parse("1989-06-03"), null, null);
        Medicalrecord mrp3 = new Medicalrecord("Tenley", "Boyd", LocalDate.parse("2012-06-02"), null, null);
        Person p1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
        Person p2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513", "drk@email.com");
        Person p3 = new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "tenz@email.com");
        List<Person> persons = new ArrayList<>();
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);
        when(personService.getPersonsFromAddress(anyString())).thenReturn(persons);
        List<Person> list = personController.getPersonsByAddressFire("1509 Culver St");
        assertEquals(3, list.size());
    }

    @Test
    public void getPersonByFirestationIdAdultTest() {
        Firestation fs1 = new Firestation(1L, "1509 Culver St", 1);
        Medicalrecord mrp1 = new Medicalrecord("John", "Boyd", LocalDate.parse("1984-06-03"), null, null);
        Person p1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
        List<Person> personList = new ArrayList<>();
        List<Firestation> firestations = new ArrayList<>();
        personList.add(p1);
        firestations.add(fs1);
        when(personService.getPersonsFromAddress(anyString())).thenReturn(personList);
        when(medicalrecordService.getPersonByFullName(anyString(), anyString())).thenReturn(Optional.of(mrp1));
        when(firestationService.getFirestationByStation(anyInt())).thenReturn(firestations);
        FirestationDTO fire = personController.getPersonByFirestationId(1);
        assertEquals(1, fire.getCountAdult());
    }


    @Test
    public void getPersonByFirestationIdChildTest() {
        Firestation fs1 = new Firestation(1L, "1509 Culver St", 1);
        Medicalrecord mrp1 = new Medicalrecord("John", "Boyd", LocalDate.parse("2012-06-03"), null, null);
        Person p1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
        List<Person> personList = new ArrayList<>();
        List<Firestation> firestations = new ArrayList<>();
        personList.add(p1);
        firestations.add(fs1);
        when(personService.getPersonsFromAddress(anyString())).thenReturn(personList);
        when(medicalrecordService.getPersonByFullName(anyString(), anyString())).thenReturn(Optional.of(mrp1));
        when(firestationService.getFirestationByStation(anyInt())).thenReturn(firestations);
        FirestationDTO fire = personController.getPersonByFirestationId(1);
        assertEquals(1, fire.getCountChild());
    }

    @Test
    public void getPhoneAlertTest() {
        Firestation fs1 = new Firestation(1L, "1509 Culver St", 1);
        Person p1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
        List<Person> lst = new ArrayList<>();
        lst.add(p1);
        when(firestationService.getFirestationByStation(anyInt())).thenReturn(Collections.singletonList(fs1));
        when(personService.getPersonsFromAddress(any())).thenReturn(lst);
        List<String> list = personController.getPhoneAlert(1);
        assertEquals(1, list.size());
    }

    @Test
    public void getPersonInfoTest() {
        PersonInfoDTO p1 = new PersonInfoDTO("John", "Doe", "1509 Culver St", "02-18-2012", "email@mail.com", null, null);
        List<PersonInfoDTO> person = new ArrayList<>();
        person.add(p1);
        when(personService.getPersonByFullName(anyString(), anyString())).thenReturn(person);
        List<PersonInfoDTO> persons = personController.getPersonInfo("John", "Doe");
        assertEquals("John", persons.get(0).getFirstname());
    }

    @Test
    public void getPersonsFromAddressTest() {
        Person p1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
        Person p2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513", "drk@email.com");
        Person p3 = new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "tenz@email.com");
        List<Person> persons = new ArrayList<>();
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);
        when(personService.getPersonsFromAddress(any())).thenReturn(persons);
        List<Person> list = personService.getPersonsFromAddress("1509 Culver St");
        assertEquals("1509 Culver St", list.get(0).getAddress());
    }

    @Test
    public void getAllPersonsTest() {
        Person p1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
        Person p2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513", "drk@email.com");
        Person p3 = new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "tenz@email.com");
        List<Person> persons = new ArrayList<>();
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);
        when(personService.getPersons()).thenReturn(persons);
        List<Person> list = personController.getAllPersons();
        assertEquals(3, list.size());
    }

    @Test
    public void addPersonTest() {
        Person p1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
        lenient().when(personService.savePerson(any())).thenReturn(p1);
        assertEquals("Boyd", p1.getLastName());
    }

    @Test
    public void getAllPersonsMedical() throws ParseException {
        ChildalertDTO chil1 = new ChildalertDTO("Boyd", "Tenley", 12, null);
        ChildalertDTO chil2 = new ChildalertDTO("Boyd", "Jacob", 56, null);
        ChildalertDTO chil3 = new ChildalertDTO("Boyd", "Jacob", 56, null);
        List<ChildalertDTO> persons = new ArrayList<>();
        persons.add(chil1);
        persons.add(chil2);
        persons.add(chil3);
        when(personService.getChildAlertDTOListFromAddress(anyString())).thenReturn(persons);
        List<ChildalertDTO> list = personController.getChildAlertList("1509 Culver St");
        assertEquals("Boyd", list.get(0).getLastName());
    }

    @Test
    public void personToFamilyMemberDTOTest() {
        Person p1 = new Person("Bobby", "LaPointe", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
        FamilyMemberDTOMapperImpl familyMemberDTOMapperImpl = new FamilyMemberDTOMapperImpl();
        FamilyMemberDTO fm1 = new FamilyMemberDTO();
        fm1.setFirstName("Bobby");
        fm1.setLastName("LaPointe");
        assertEquals(fm1, familyMemberDTOMapperImpl.personToFamilyMemberDTO(p1));
    }

    @Test
    public void personListToFamilyMemberDTOListTest() {
        Person p1 = new Person("Bobby", "LaPointe", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
        List<Person> persons = new ArrayList<>();
        persons.add(p1);
        FamilyMemberDTOMapperImpl familyMemberDTOMapperImpl = new FamilyMemberDTOMapperImpl();
        FamilyMemberDTO fm1 = new FamilyMemberDTO();
        fm1.setFirstName("Bobby");
        fm1.setLastName("LaPointe");
        List<FamilyMemberDTO> familyMemberDTOS = new ArrayList<>();
        familyMemberDTOS.add(fm1);
        assertEquals(Collections.singletonList(fm1), familyMemberDTOMapperImpl.personListToFamilyMemberDTOList(persons));
    }
}
