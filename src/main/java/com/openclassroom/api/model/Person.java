package com.openclassroom.api.model;



import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor

@Table(name = "persons")
@Entity @IdClass(PersonId.class)
@Getter
@Setter
@Generated
@EqualsAndHashCode
public class Person {
    @Id
    private String firstName;

    @Id
    private String lastName;

    private String address;
    private String city;
    private Integer zip;
    private String phone;
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Medicalrecord.class)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Medicalrecord medicalrecord;
}
