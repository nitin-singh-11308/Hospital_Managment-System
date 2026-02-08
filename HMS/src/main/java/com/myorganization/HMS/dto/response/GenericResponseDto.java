package com.myorganization.HMS.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class GenericResponseDto {
    private boolean success;
    private String message;
    private Object details;

}
