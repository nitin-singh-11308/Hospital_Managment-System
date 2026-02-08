package com.myorganization.HMS.dto.response;

import com.myorganization.HMS.enums.DoctorCategory;
import lombok.Data;

@Data
public class DoctorResponseDto {
    private Long id;
    private String name;
    private Long phone;
    private String email;
    private String address;
    private DoctorCategory doctorCategory;
}
