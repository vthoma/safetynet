package com.openclassroom.api.service;

import com.openclassroom.api.dto.FirestationDTO;
import com.openclassroom.api.model.Firestation;
import com.openclassroom.api.repository.FirestationRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
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
public class FirestationService {

    private static final Logger logger = LogManager.getLogger(PersonService.class);

    @Autowired
    private FirestationRepository firestationRepository;

    public Optional<Firestation> getFirestation(final Long id) {
        return firestationRepository.findById(id);
    }

    public Iterable<Firestation> getFirestations() {
        return firestationRepository.findAll();
    }

    public Integer deleteFireStationByAddress(String address) {
        return firestationRepository.deleteByAddressIgnoreCase(address);
    }

    public List<Firestation> getFirestationByStation(int station) {
        return firestationRepository.findByStation(station);
    }

//    public FirestationDTO firestationDTO(Person person) {
//        FirestationDTO firestationDTO = new FirestationDTO();
//        firestationDTO.setData(person.getFirstname(), person.getLastname());
//        return firestationDTO;
//    }

    public boolean saveAllFirestations(List<Firestation> firestationList) {
        if (firestationList != null) {
            try {
                firestationRepository.saveAll(firestationList);
                return true;
            } catch (Exception exception) {
                logger.error("Erreur lors de l'enregistrement de la liste des personnes " + exception.getMessage() + " , Stack Trace : " + exception.getStackTrace());
            }
        }
        return false;
    }
    public Firestation saveFirestation(Firestation firestation) {
        return firestationRepository.save(firestation);
    }
}
