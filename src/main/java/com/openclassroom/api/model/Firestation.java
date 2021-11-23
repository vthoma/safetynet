package com.openclassroom.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "firestations")
@Getter
@Setter
@Generated
@EqualsAndHashCode
public class Firestation {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String address;
    private int station;
}
