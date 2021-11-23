package com.openclassroom.api.repository;

import com.openclassroom.api.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByCity(String city);
    List<Person> findByAddress(String address);
    List<Person> findByFirstnameAndLastname(String firstname, String lastname);
}
