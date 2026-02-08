package com.myorganization.HMS.controller;

import com.myorganization.HMS.dto.request.PatientRequestDto;
import com.myorganization.HMS.dto.response.GenericResponseDto;
import com.myorganization.HMS.dto.response.PatientResponseDto;
import com.myorganization.HMS.repository.PatientRepository;
import com.myorganization.HMS.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    PatientService patientService;

    @Autowired
    PatientRepository patientRepository;

    @PostMapping
    public ResponseEntity<PatientResponseDto> addPatient(@RequestBody PatientRequestDto patientRequestDto) {
        return new ResponseEntity<>(patientService.addPatient(patientRequestDto), HttpStatusCode.valueOf(201));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDto> getPatient(@PathVariable Long id) {
        return new ResponseEntity<>(patientService.getPatient(id), HttpStatusCode.valueOf(200));
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDto>> getAllPatient() {
        return new ResponseEntity<>(patientService.getAllPatient(), HttpStatusCode.valueOf(200));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDto> updatePatient(@PathVariable Long id, @RequestBody PatientRequestDto patientRequestDto) {
        return new ResponseEntity<>(patientService.updatePatient(id, patientRequestDto), HttpStatusCode.valueOf(200));
    }

    @DeleteMapping
    public ResponseEntity<GenericResponseDto> deletePatient(@PathVariable Long id) {
        return new ResponseEntity<>(patientService.removePatient(id), HttpStatusCode.valueOf(200));
    }
}
