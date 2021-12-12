package com.openclassroom.api.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity @IdClass(MedicalRecordId.class)
@Table(name = "medicalrecords")
@Generated
@EqualsAndHashCode
public class Medicalrecord {
    @Id
    private String firstName;

    @Id
    private String lastName;

    private String birthdate;

    @ElementCollection
    private List<String> medications;

    @ElementCollection
    private List<String> allergies;
}
