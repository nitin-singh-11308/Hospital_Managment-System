package com.myorganization.HMS.dto.request;

import lombok.Data;

@Data
public class PatientRequestDto {
    private String name;
    private Long phone;
    private String email;
    private String address;

    private Long doctorId;

}
