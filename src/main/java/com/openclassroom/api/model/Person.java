package com.openclassroom.api.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "persons")
@Getter
@Setter
@Generated
@EqualsAndHashCode
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private String address;
    private String city;
    private Integer zip;
    private String phone;
    private String email;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Medicalrecord medicalrecord;

}
