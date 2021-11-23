package com.openclassroom.api.service;

import com.openclassroom.api.dto.FirestationDTO;
import com.openclassroom.api.model.Firestation;
import com.openclassroom.api.model.Person;
import com.openclassroom.api.repository.FirestationRepository;
import com.openclassroom.api.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scala.Int;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Getter
@Setter
@Service
public class FirestationService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FirestationRepository firestationRepository;

    public Optional<Firestation> getFirestation(final Long id) {
        return firestationRepository.findById(id);
    }

    public Iterable<Firestation> getFirestations() {
        return firestationRepository.findAll();
    }

    public void deleteFirestation(final Long id) {
        firestationRepository.deleteById(id);
    }

    public List<Firestation> getFirestationByStation(int station) {
        return firestationRepository.findByStation(station);
    }



    public FirestationDTO firestationDTO(Person person) {
        FirestationDTO firestationDTO = new FirestationDTO();
        firestationDTO.setData(person.getFirstname(), person.getLastname());
        return firestationDTO;
    }
    public Firestation saveFirestation(Firestation firestation) {
        return firestationRepository.save(firestation);
    }
}
