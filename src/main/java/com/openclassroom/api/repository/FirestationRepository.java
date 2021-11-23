package com.openclassroom.api.repository;

import com.openclassroom.api.model.Firestation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FirestationRepository extends JpaRepository<Firestation, Long> {
    List<Firestation> findByStation(int station);
    List<Firestation> findByAddress(String address);
}
