package com.openclassroom.api.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class MedicalrecordId implements Serializable {
    private String firstName;
    private String lastName;
}