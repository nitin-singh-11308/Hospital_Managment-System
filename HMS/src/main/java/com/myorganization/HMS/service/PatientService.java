package com.myorganization.HMS.service;

import com.myorganization.HMS.dto.request.PatientRequestDto;
import com.myorganization.HMS.dto.response.GenericResponseDto;
import com.myorganization.HMS.dto.response.PatientResponseDto;

import java.util.List;

public interface PatientService {
    PatientResponseDto addPatient(PatientRequestDto patientRequestDto);
    PatientResponseDto getPatient(Long id);
    List<PatientResponseDto> getAllPatient();
    PatientResponseDto updatePatient(Long id, PatientRequestDto patientRequestDto);
    GenericResponseDto removePatient(Long id);
}
