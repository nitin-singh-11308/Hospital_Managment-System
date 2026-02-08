package com.myorganization.HMS.dto.response;

import lombok.Data;

@Data
public class PatientResponseDto {
    private Long id;
    private String name;
    private Long phone;
    private String email;
    private String address;

}
