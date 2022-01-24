package com.openclassroom.api.controller;

import com.openclassroom.api.model.Firestation;
import com.openclassroom.api.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class FirestationController {

    @Autowired
    private FirestationService firestationService;

    @PostMapping("/firestation")
    public Firestation addFirestation(@RequestBody Firestation firestation) {
        return firestationService.saveFirestation(firestation);
    }
    @DeleteMapping("/firestation/address")
    public Integer deleteFirestation(@PathVariable String address) {
        Integer deleteResult = firestationService.deleteFireStationByAddress(address);
        return deleteResult;
    }

}
