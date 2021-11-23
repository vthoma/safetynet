package com.openclassroom.api.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medicalrecords")
@Generated
@EqualsAndHashCode
public class Medicalrecord {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private String birthdate;
    private String medications;
    private String allergies;
}
