package com.myorganization.HMS.exception;

import com.myorganization.HMS.dto.response.GenericResponseDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {
    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<GenericResponseDto> handleDoctorNotFoundException(DoctorNotFoundException e) {
        GenericResponseDto genericResponseDto = new GenericResponseDto();

        genericResponseDto.setSuccess(false);
        genericResponseDto.setMessage(e.getMessage());
        genericResponseDto.setDetails(null);

        return new ResponseEntity<>(genericResponseDto, HttpStatusCode.valueOf(404));
    }

}
