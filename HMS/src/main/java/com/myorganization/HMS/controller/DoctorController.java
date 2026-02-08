package com.myorganization.HMS.controller;

import com.myorganization.HMS.dto.request.DoctorRequestDto;
import com.myorganization.HMS.dto.response.DoctorResponseDto;
import com.myorganization.HMS.dto.response.GenericResponseDto;
import com.myorganization.HMS.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorResponseDto> addDoctor(@RequestBody DoctorRequestDto doctorRequestDto) {
        return new ResponseEntity<> (doctorService.addDoctor(doctorRequestDto), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> getDoctor(@PathVariable Long id) {
        return new ResponseEntity<>(doctorService.getDoctor(id), HttpStatusCode.valueOf(200));
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponseDto>> getAllDoctor() {
        return new ResponseEntity<>(doctorService.getAllDoctor(), HttpStatusCode.valueOf(200));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> updateDoctor(@PathVariable Long id, @RequestBody DoctorRequestDto doctorRequestDto) {
        return new ResponseEntity<>(doctorService.updateDoctor(id, doctorRequestDto), HttpStatusCode.valueOf(201));
    }

    @DeleteMapping
    public ResponseEntity<GenericResponseDto> deleteDoctor(@RequestParam Long id) {
        return new ResponseEntity<>(doctorService.deleteDoctor(id), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<DoctorResponseDto>> getDoctorPage (
            @RequestParam(defaultValue = "0") Integer pageIndex,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortByAttribute,
            @RequestParam(defaultValue = "asc") String sortInOrder
    ) {
        return new ResponseEntity<>(doctorService.getDoctorPage(pageIndex,pageSize, sortByAttribute, sortInOrder), HttpStatusCode.valueOf(200));
    }

}
