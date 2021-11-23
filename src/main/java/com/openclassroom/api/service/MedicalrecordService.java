package com.openclassroom.api.service;

import com.openclassroom.api.model.Medicalrecord;
import com.openclassroom.api.repository.MedicalrecordRepository;
import com.openclassroom.api.repository.PersonRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Getter
@Setter
@Service
public class MedicalrecordService {
    @Autowired
    private MedicalrecordRepository medicalrecordRepository;
    private PersonRepository personRepository;

    public Optional<Medicalrecord> getMedicalrecord(final Long id) {
        return medicalrecordRepository.findById(id);
    }

    public Iterable<Medicalrecord> getMedicalrecord() {
        return medicalrecordRepository.findAll();
    }

    public Optional<Medicalrecord> getPersonByFullName(final String firstname, final String lastname) {
        return medicalrecordRepository.findByFirstnameAndLastname(firstname, lastname);
    }

    public void deleteMedicalrecord(final Long id) {
        medicalrecordRepository.deleteById(id);
    }

    public Medicalrecord saveMedicalrecord(Medicalrecord medicalrecord) {
        return medicalrecordRepository.save(medicalrecord);
    }
}
