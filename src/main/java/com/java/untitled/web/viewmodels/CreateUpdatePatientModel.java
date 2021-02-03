package com.java.untitled.web.viewmodels;

import com.java.untitled.data.entity.Doctor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateUpdatePatientModel {

    @NotBlank
    @Size(min = 5, max = 15, message = "Min 5, Max 15")
    private String name;

    @NotBlank
    @Size(min = 10, max = 11, message = "Min 10, Max 11")
    private String EGN;

    private Doctor GP;
}
