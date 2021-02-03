package com.java.untitled.web.viewmodels;

import com.java.untitled.data.entity.Specialty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateUpdateDoctorModel {

    @NotBlank
    @Size(min = 5, max = 15, message = "Min 5, Max 15")
    private String name;

    @NotBlank
    @Size(min = 9, max = 10, message = "Min 9, Max 10")
    private String identification;

    private Specialty specialty;

    @NotNull
    private String isGP;
}
