package com.myorganization.HMS.service;

import com.myorganization.HMS.dto.request.PatientRequestDto;
import com.myorganization.HMS.dto.response.GenericResponseDto;
import com.myorganization.HMS.dto.response.PatientResponseDto;
import com.myorganization.HMS.exception.PatientNotFoundException;
import com.myorganization.HMS.model.Doctor;
import com.myorganization.HMS.model.Patient;
import com.myorganization.HMS.repository.DoctorRepository;
import com.myorganization.HMS.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public PatientResponseDto addPatient(PatientRequestDto patientRequestDto) {
        Patient patient = new Patient();
        patient = mapPatientRequestDtoToPatient(patient, patientRequestDto);

        Doctor doctor = doctorRepository.findById(patientRequestDto.getDoctorId()).orElseThrow(()-> new PatientNotFoundException("Patient not found"));
        patient.setDoctor(doctor);

        patientRepository.save(patient);

        return mapPatientToPatientResponseDto(patient);
    }

    @Override
    public PatientResponseDto getPatient(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new PatientNotFoundException("Patient id: "+id+" not found"));

        return mapPatientToPatientResponseDto(patient);
    }

    @Override
    public List<PatientResponseDto> getAllPatient() {
        List <Patient> patientList = new ArrayList<>(patientRepository.findAll());

        List<PatientResponseDto> patientResponseDtoList = new ArrayList<>();

        if (patientList.isEmpty()) {
            throw new PatientNotFoundException("Patient list is empty");
        }

        for (Patient patient : patientList) {
            patientResponseDtoList.add(mapPatientToPatientResponseDto(patient));
        }
        return patientResponseDtoList;
    }

    @Override
    public PatientResponseDto updatePatient(Long id, PatientRequestDto patientRequestDto) {
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new PatientNotFoundException("Patient id: "+id+" not exist"));

        patient = mapPatientRequestDtoToPatient(patient, patientRequestDto);

        patientRepository.save(patient);

        return mapPatientToPatientResponseDto(patient);
    }

    @Override
    public GenericResponseDto removePatient(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new PatientNotFoundException("Patient id: "+id+" not exist"));

        GenericResponseDto genericResponseDto = new GenericResponseDto();

            patientRepository.deleteById(id);

            String name = patient.getName();
            String message = "Patient name: "+name+" has been removed successfully";
            genericResponseDto.setSuccess(true);
            genericResponseDto.setMessage(message);
            genericResponseDto.setDetails(null);
        return genericResponseDto;
    }


// Helper Methods

    // Map Patient To PatientRequestDto
    private PatientResponseDto mapPatientToPatientResponseDto(Patient patient) {
        PatientResponseDto patientResponseDto = new PatientResponseDto();

        patientResponseDto.setId(patient.getId());
        patientResponseDto.setName(patient.getName());
        patientResponseDto.setPhone(patient.getPhone());
        patientResponseDto.setEmail(patient.getEmail());
        patientResponseDto.setAddress(patient.getAddress());

        return patientResponseDto;
    }

    // Map PatientRequestDto to Patient
    private Patient mapPatientRequestDtoToPatient (Patient patient, PatientRequestDto patientRequestDto) {
        patient.setName(patientRequestDto.getName());
        patient.setPhone(patientRequestDto.getPhone());
        patient.setEmail(patientRequestDto.getEmail());
        patient.setAddress(patientRequestDto.getAddress());

        return patient;
    }
}
