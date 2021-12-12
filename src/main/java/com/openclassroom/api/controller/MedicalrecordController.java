package com.openclassroom.api.controller;

import com.openclassroom.api.service.MedicalrecordService;
import com.openclassroom.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicalrecordController {
    @Autowired
    private MedicalrecordService medicalrecordService;
    private PersonService personService;


}
