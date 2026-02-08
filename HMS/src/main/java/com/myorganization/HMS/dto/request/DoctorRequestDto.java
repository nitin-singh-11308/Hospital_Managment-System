package com.myorganization.HMS.dto.request;

import com.myorganization.HMS.enums.DoctorCategory;
import lombok.Data;

@Data
public class DoctorRequestDto {
    private String name;
    private Long phone;
    private String email;
    private String address;
    private DoctorCategory doctorCategory;

}
