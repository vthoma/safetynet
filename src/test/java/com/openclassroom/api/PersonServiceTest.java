//package com.openclassroom.api;
//
//import com.openclassroom.api.model.Person;
//import com.openclassroom.api.repository.PersonRepository;
//import com.openclassroom.api.service.PersonService;
//import org.aspectj.lang.annotation.Before;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//public class PersonServiceTest {
//
//    @Autowired
//    private TestEntityManager entityManager;
//    @Autowired
//    private PersonRepository personRepository;
//    @Autowired
//    private PersonService personService;
//
//    @BeforeEach
//    public void setup() {
//        assertNotNull(entityManager);
//        assertNotNull(personRepository);
//        assertNotNull(personService);
//
//        Person john = new Person();
//        john.setFirstname("John");
//        john.setLastname("Doe");
//        john.setCity("London");
//        john.setAddress("50 St. Culver");
//        john.setEmail("email@email.com");
//        john.setPhone("555-555");
//        john.setZip(75000);
//        entityManager.persist(john);
//
//        Person jane = new Person();
//        jane.setFirstname("Jane");
//        jane.setLastname("Doe");
//        jane.setCity("Paris");
//        jane.setAddress("55 St. Culver");
//        jane.setEmail("test@email.com");
//        jane.setPhone("555-000");
//        jane.setZip(75000);
//        entityManager.persist(jane);
//    }
//
//    @Test
//    public void findAll_return_list_when_found() {
//        List<Person> found = personService.getPersons();
//        assertNotNull(found);
//    }
////    @Test
////    public void findByAddress_return_person_when_found() {
////        List<Person> found = personRepository.findByAddress("50 St. Culver");
////        assertNotNull(found);
////        assertEquals("50 St. Culver", found.get(0).getAddress());
////    }
//}
