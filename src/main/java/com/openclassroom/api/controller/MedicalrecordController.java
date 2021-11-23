package com.openclassroom.api.controller;

import com.openclassroom.api.model.Medicalrecord;
import com.openclassroom.api.model.Person;
import com.openclassroom.api.service.MedicalrecordService;
import com.openclassroom.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicalrecordController {
    @Autowired
    private MedicalrecordService medicalrecordService;
    private PersonService personService;

    @GetMapping("medicalrecords")
    public Iterable<Medicalrecord> getMedicalrecord() {
        return medicalrecordService.getMedicalrecord();
    }

}
