package com.myorganization.HMS.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myorganization.HMS.enums.DoctorCategory;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long phone;
    private String email;
    private String address;


    @Enumerated(EnumType.STRING)
    private DoctorCategory doctorCategory;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Patient> patients;
}
