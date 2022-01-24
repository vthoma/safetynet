package com.openclassroom.api.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity @IdClass(PersonId.class)
@Table(name="Persons")
@EqualsAndHashCode
@NoArgsConstructor
@Generated
@AllArgsConstructor
public class Person {

    @Id
    @NotNull
    @NotEmpty
    private String firstName;

    @Id
    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String address;

    @NotNull
    @NotEmpty
    private String city;

    @NotNull
    @NotEmpty
    private String zip;

    @NotNull
    @NotEmpty
    private String phone;

    @NotNull
    @NotEmpty
    private String email;

}