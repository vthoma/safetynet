package com.openclassroom.api.repository;

import com.openclassroom.api.model.Medicalrecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicalrecordRepository extends CrudRepository<Medicalrecord, Long> {
    Optional<Medicalrecord> findByFirstnameAndLastname(String firstname, String lastname);
}
