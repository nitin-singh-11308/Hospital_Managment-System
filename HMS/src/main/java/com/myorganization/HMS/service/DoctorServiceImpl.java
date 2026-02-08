package com.myorganization.HMS.service;

import com.myorganization.HMS.dto.request.DoctorRequestDto;
import com.myorganization.HMS.dto.response.DoctorResponseDto;
import com.myorganization.HMS.dto.response.GenericResponseDto;
import com.myorganization.HMS.model.Doctor;
import com.myorganization.HMS.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    private DoctorRepository doctorRepository;


    @Override
    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) {
        Doctor doctor = new Doctor();
        doctor = mapDoctorRequestDtoToDoctor(doctor, doctorRequestDto);

        doctorRepository.save(doctor);

        return mapDoctorToDoctorResponseDto(doctor);
    }

    @Override
    public DoctorResponseDto getDoctor(Long id) {

        Doctor doctor = doctorRepository.findById(id).orElse(null);

        return mapDoctorToDoctorResponseDto(doctor);
    }

    @Override
    public List<DoctorResponseDto> getAllDoctor() {
        List<Doctor> doctorList = new ArrayList<>(doctorRepository.findAll());

        List<DoctorResponseDto> doctorResponseDtoList = new ArrayList<>();

        for (Doctor doctor : doctorList) {
            doctorResponseDtoList.add(mapDoctorToDoctorResponseDto(doctor));
        }

        return doctorResponseDtoList;
    }

    @Override
    public DoctorResponseDto updateDoctor(Long id, DoctorRequestDto doctorRequestDto) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);

        doctor = mapDoctorRequestDtoToDoctor(doctor, doctorRequestDto);

        doctorRepository.save(doctor);

        return mapDoctorToDoctorResponseDto(doctor);
    }

    @Override
    public GenericResponseDto deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);

        GenericResponseDto genericResponseDto = new GenericResponseDto();

        if (doctor != null) {
            String name = doctor.getName();

            doctorRepository.deleteById(id);

            genericResponseDto.setSuccess(true);
            genericResponseDto.setMessage("Doctor ("+id+") : " +name+ " has been removed successfully." );

            return genericResponseDto;
        }
        else {
            genericResponseDto.setSuccess(false);
            genericResponseDto.setMessage("Doctor (" +id+") Not found.");

            return genericResponseDto;
        }

    }

    @Override
    public Page<DoctorResponseDto> getDoctorPage(Integer pageIndex, Integer pageSize, String sortByAttribute, String sortInOrder) {
        Sort sort = sortInOrder.equalsIgnoreCase("desc") ? Sort.by(sortByAttribute).descending() : Sort.by(sortByAttribute).ascending();

        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);

        Page<Doctor> doctorPage = doctorRepository.findAll(pageable);

        Page<DoctorResponseDto> doctorResponseDtoPage = doctorPage.map(doctor -> mapDoctorToDoctorResponseDto(doctor));

        return doctorResponseDtoPage;
    }

    //Helper Methods

    //Map Doctor To DoctorResponseDto
    private DoctorResponseDto mapDoctorToDoctorResponseDto(Doctor doctor) {
        DoctorResponseDto doctorResponseDto = new DoctorResponseDto();

        doctorResponseDto.setId(doctor.getId());
        doctorResponseDto.setName(doctor.getName());
        doctorResponseDto.setPhone(doctor.getPhone());
        doctorResponseDto.setEmail(doctor.getEmail());
        doctorResponseDto.setAddress(doctor.getAddress());
        doctorResponseDto.setDoctorCategory(doctor.getDoctorCategory());

        return doctorResponseDto;
    }

    // Map DoctorRequestDto to Doctor
    private Doctor mapDoctorRequestDtoToDoctor(Doctor doctor, DoctorRequestDto doctorRequestDto) {
        doctor.setName(doctorRequestDto.getName());
        doctor.setPhone(doctorRequestDto.getPhone());
        doctor.setEmail(doctorRequestDto.getEmail());
        doctor.setAddress(doctorRequestDto.getAddress());
        doctor.setDoctorCategory(doctorRequestDto.getDoctorCategory());

        return doctor;
    }
}
