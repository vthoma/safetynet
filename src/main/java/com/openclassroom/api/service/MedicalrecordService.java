package com.openclassroom.api.service;

import com.openclassroom.api.model.Medicalrecord;
import com.openclassroom.api.repository.MedicalrecordRepository;
import com.openclassroom.api.repository.PersonRepository;
import lombok.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Getter
@Setter
@Service
public class MedicalrecordService {

    private static final Logger logger = LogManager.getLogger(PersonService.class);

    @Autowired
    private MedicalrecordRepository medicalrecordRepository;

    @Autowired
    private PersonRepository personRepository;

//    public Optional<Medicalrecord> getMedicalrecord(final Long id) {
//        return medicalrecordRepository.findById(id);
//    }

//    public Iterable<Medicalrecord> getMedicalrecord() {
//        return medicalrecordRepository.findAll();
//    }

    public Optional<Medicalrecord> getPersonByFullName(final String firstname, final String lastname) {
        return medicalrecordRepository.findByFirstNameAndLastName(firstname, lastname);
    }

//    public void deleteMedicalrecord(final Long id) {
//        medicalrecordRepository.deleteById(id);
//    }

    public boolean saveAllMedicalRecords(List<Medicalrecord> lstMedicalRecords) {
        if (lstMedicalRecords != null) {
            try {
                medicalrecordRepository.saveAll(lstMedicalRecords);
                return true;
            } catch (Exception exception) {
                logger.error("Erreur lors de l'enregistrement de la liste des personnes " + exception.getMessage() + " , Stack Trace : " + exception.getStackTrace());
            }
        }
        return false;
    }

    public Medicalrecord saveMedicalrecord(Medicalrecord medicalrecord) {
        return medicalrecordRepository.save(medicalrecord);
    }
}
