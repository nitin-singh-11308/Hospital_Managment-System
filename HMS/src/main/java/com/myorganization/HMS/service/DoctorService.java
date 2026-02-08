package com.myorganization.HMS.service;

import com.myorganization.HMS.dto.request.DoctorRequestDto;
import com.myorganization.HMS.dto.response.DoctorResponseDto;
import com.myorganization.HMS.dto.response.GenericResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DoctorService {
    DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto);
    DoctorResponseDto getDoctor(Long id);
    List<DoctorResponseDto> getAllDoctor();
    DoctorResponseDto updateDoctor(Long id, DoctorRequestDto doctorRequestDto);
    GenericResponseDto deleteDoctor(Long id);

    Page<DoctorResponseDto> getDoctorPage(Integer pageIndex, Integer pageSize, String sortByAttribute, String sortInOrder);

}
