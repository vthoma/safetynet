package com.openclassroom.api.controller;

import com.openclassroom.api.model.Firestation;
import com.openclassroom.api.model.Medicalrecord;
import com.openclassroom.api.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirestationController {

    @Autowired
    private FirestationService firestationService;

}
